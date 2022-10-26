package com.radumarinescu.multiplesheets.welcome

import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    enum class SheetType {
        Intro,
        Benefits,
        Contact
    }
}