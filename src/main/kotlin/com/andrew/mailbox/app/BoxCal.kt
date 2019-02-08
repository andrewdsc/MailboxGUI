package com.andrew.mailbox.app

import com.andrew.mailbox.model.IBox

fun main(args: Array<String>) {

}

class Box3Class(): IBox{
    override fun validate(length: Float, width: Float, height: Float): Boolean  {
        var error:Int = 0
        if(width > 14)
        {
            error += 1
        }

        if(height > 13)
        {
            error += 1
        }

        return error <= 0
    }

}

class Box5Class(): IBox{
    override fun validate(length: Float, width: Float, height: Float) : Boolean {
        var error:Int = 0
        if(width > 27.5)
        {
            error += 1
        }

        if(height > 23)
        {
            error += 1
        }

        return error <= 0
    }

}