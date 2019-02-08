package com.andrew.mailbox.test

fun main(args: Array<String>) {
    outer@ for (x in 1..5) {
        for (y in 1..5) {
            if (y > 3) {
                println("=>X:$x")
                continue@outer
            }

            print(y)
        }


    }
}