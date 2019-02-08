package com.andrew.mailbox.view

import com.andrew.mailbox.app.Box5Class
import com.andrew.mailbox.app.Box3Class
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import tornadofx.*

class MainView : View("包裹容量計算") {
    val controller: MyController by inject()
    var lengthField: TextField by singleAssign()
    var widthField: TextField by singleAssign()
    var heightField: TextField by singleAssign()
    var resultlabel: Label by singleAssign()

    override val root = form {
        fieldset("郵件包裹容量計算") {
            field("長") {
                lengthField = textfield()
            }
            field("寬") {
                widthField = textfield()
            }
            field("高") {
                heightField = textfield()
            }
        }
        button("按下後計算") {
            action {
                if (!lengthField.equals("")) {
                    var result = controller.writeToDb(lengthField.text, widthField.text, heightField.text)
                    resultlabel.text = "計算結果:$result"
                }
                else
                {
                    resultlabel.text = "請將長寬高資料都輸入完成!!!"
                }
            }

        }

        resultlabel = label("計算結果") {
            vboxConstraints {
                marginTop = 20.0
                vGrow = Priority.ALWAYS
            }
        }
    }
}


class MyController : Controller() {
    fun writeToDb(lengthValue: String, widthValue: String, heightValue: String): String {
//        return "你輸入是${inputValue}"
        if (lengthValue.toFloat() > 23) //如果箱子大於 23公分 只能放入Box5
        {
            var result = Box5Class().validate(lengthValue.toFloat(), widthValue.toFloat(), heightValue.toFloat())
            if (result) {
                return "你的箱子可以放入Box5之中"
            }
        } else if (lengthValue.toFloat() <= 23) {
            var result = Box3Class().validate(lengthValue.toFloat(), widthValue.toFloat(), heightValue.toFloat())
            if (result) {
                return "你的箱子可以放入Box3之中"
            }
        }
        return "你的箱子無法放入現有的箱子尺寸中"
    }
}

