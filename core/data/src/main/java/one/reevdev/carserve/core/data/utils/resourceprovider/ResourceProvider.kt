package one.reevdev.carserve.core.data.utils.resourceprovider

import java.io.InputStream

interface ResourceProvider {
    fun getRawResourceAsStream(resourceId: Int): InputStream
}