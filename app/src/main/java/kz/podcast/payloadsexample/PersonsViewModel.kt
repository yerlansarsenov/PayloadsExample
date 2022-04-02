package kz.podcast.payloadsexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kz.podcast.payloadsexample.models.InternetStatus
import kz.podcast.payloadsexample.models.Person
import kz.podcast.payloadsexample.utils.generatePersons

class PersonsViewModel : ViewModel() {

    private val _personsFlow = MutableStateFlow<List<Person>>(emptyList())
    val personsFlow: StateFlow<List<Person>>
        get() = _personsFlow

    private val persons by lazy { generatePersons() }

    init {
        viewModelScope.launch { getPersons() }
        viewModelScope.launch { getPersonsStatuses() }
    }

    private suspend fun getPersons() {
        delay(200)
        _personsFlow.emit(persons)
    }

    private suspend fun getPersonsStatuses() {
        delay(3000)
        val updatedPersons = persons.map { person ->
            return@map when {
                person.id % 3 == 0 -> person.copy(internetStatus = InternetStatus.Offline)
                person.id % 2 == 0 -> person.copy(internetStatus = InternetStatus.Online)
                else -> person
            }
        }
        _personsFlow.emit(updatedPersons)
    }
}
