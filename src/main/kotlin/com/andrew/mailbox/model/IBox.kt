package com.andrew.mailbox.model

interface IBox {
    fun validate(length:Float, width:Float, height:Float): Boolean
}