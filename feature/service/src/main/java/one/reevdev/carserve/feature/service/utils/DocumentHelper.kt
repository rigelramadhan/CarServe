package one.reevdev.carserve.feature.service.utils

import android.content.ContentUris
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.print.PDFPrint.OnPDFPrintListener
import android.provider.MediaStore
import com.tejpratapsingh.pdfcreator.utils.PDFUtil
import java.io.File
import java.io.FileInputStream
import java.io.IOException


object DocumentHelper {
    fun printPdf(analysisResultHtml: String, context: Context, onViewerUnavailable: (String) -> Unit) {
        val tempPdfFile = createTempPdfFile(context)

        PDFUtil.generatePDFFromHTML(
            context,
            tempPdfFile,
            analysisResultHtml,
            object : OnPDFPrintListener {
                override fun onSuccess(file: File?) {
                    try {
                        val pdfFile =
                            savePdfToDocuments(context, tempPdfFile, "analysis_${System.currentTimeMillis()}.pdf")
                        openPdf(pdfFile, context) { onViewerUnavailable(pdfFile.toFilePath(context)) }
                    } finally {
                        tempPdfFile.delete()
                    }
                }

                override fun onError(exception: Exception?) {
                    exception?.printStackTrace()
                }
            })
    }

    @Throws(IOException::class)
    private fun savePdfToDocuments(context: Context, pdfFile: File, fileName: String): Uri {
        val contentResolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "application/pdf")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOCUMENTS)
        }

        val uri: Uri = contentResolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
            ?: throw IOException("Failed to create new MediaStore record.")

        contentResolver.openOutputStream(uri)?.use { outputStream ->
            FileInputStream(pdfFile).use { inputStream ->
                inputStream.copyTo(outputStream)
            }
        } ?: throw IOException("Failed to get output stream.")

        return uri
    }

    private fun Uri.toFilePath(context: Context): String {
        val contentResolver = context.contentResolver
        val id = ContentUris.parseId(this)
        val filePathUri = MediaStore.Files.getContentUri("external", id)
        var filePath: String? = null
        contentResolver.query(filePathUri, arrayOf(MediaStore.MediaColumns.DATA), null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA))
            }
        }

        return filePath.orEmpty()
    }

    private fun openPdf(pdfUri: Uri, context: Context, onViewerUnavailable: () -> Unit) {
        try {
            openPdfViaIntent(context, pdfUri)
        } catch (e: Exception) {
            onViewerUnavailable()
        }
    }

    @Throws(IOException::class)
    private fun createTempPdfFile(context: Context): File {
        return File.createTempFile("temp_pdf_", ".pdf", context.cacheDir)
    }

    private fun openPdfViaIntent(context: Context, uri: Uri) {
        Intent(Intent.ACTION_VIEW).apply {
            data = uri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            context.startActivity(this)
        }
    }
}