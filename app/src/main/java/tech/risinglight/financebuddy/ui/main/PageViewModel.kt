package tech.risinglight.financebuddy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {

    private val _index = MutableLiveData<Int>()
    val text: LiveData<Int> = Transformations.map(_index) {
        it
    }

    fun getWebsite(index: Int): String {
        when (index) {
            1 -> return "https://www.grabon.in/electronics-coupons/"
            2 -> return "https://www.coupondunia.in/category/food-and-dining"
            3 -> return "https://www.grabon.in/clothing-coupons/"
            4 -> return "https://www.grabon.in/movie-tickets-coupons/"
            5 -> return "https://www.grabon.in/medicines-coupons/"
        }

        return "https://www.grabon.in/electronics-coupons/"
    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}