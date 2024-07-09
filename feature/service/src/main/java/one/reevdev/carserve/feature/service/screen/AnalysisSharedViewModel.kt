package one.reevdev.carserve.feature.service.screen

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import one.reevdev.carserve.core.domain.model.service.ServiceParam
import javax.inject.Inject

@HiltViewModel
class AnalysisSharedViewModel @Inject constructor() : ViewModel() {

    private val _param = MutableStateFlow(ServiceParam())
    val param: StateFlow<ServiceParam> by lazy { _param }

    fun setSymptoms(symptoms: String) {
        _param.update {
            it.copy(symptoms = symptoms)
        }
    }

    fun setGeneralProblem(generalProblem: String) {
        _param.update {
            it.copy(generalProblem = generalProblem)
        }
    }

    fun setPhoto(photo: Bitmap?) {
        _param.update {
            it.copy(photo = photo)
        }
    }
}