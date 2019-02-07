package com.andrew.mailbox.view

import com.andrew.mailbox.app.Styles
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.TextField
import tornadofx.*

class MainView : View("包裹容量計算") {
    val controller: MyController by inject()
    var inputField: TextField by singleAssign()
    var inlabel: Label by singleAssign()

    override val root = vbox {
        inlabel = label("Input")
        inputField = textfield()
        button("Commit") {
            action {
                var result = controller.writeToDb(inputField.text)
                inputField.clear()
                inlabel.text = result
            }
        }
    }

}


class MyController: Controller() {
    fun writeToDb(inputValue: String): String {
        return "你輸入是${inputValue}"
    }
}