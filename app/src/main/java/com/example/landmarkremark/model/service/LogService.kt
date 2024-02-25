package com.example.landmarkremark.model.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}