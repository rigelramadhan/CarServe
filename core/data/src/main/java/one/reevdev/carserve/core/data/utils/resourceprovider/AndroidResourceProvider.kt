package one.reevdev.carserve.core.data.utils.resourceprovider

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class AndroidResourceProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceProvider {
    override fun getRawResourceAsStream(resourceId: Int): InputStream {
        return context.resources.openRawResource(resourceId)
    }
}