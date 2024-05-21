package com.cr.databindingdemo.bean

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cr.databindingdemo.BR

class User1 : BaseObservable() {

    @get:Bindable
    var userName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.userName)
        }

    @get:Bindable // 这是 Kotlin 中的属性访问器注解。在这个代码中，它应用在 get 方法上，表示这个 userId 属性可以被绑定。
    var userId: String = ""
        //这是一个字符串类型的属性 userId，它被初始化为空字符串。这个属性是可变的（var）。
        set(value) {
            // 这是 userId 属性的设置方法。当给 userId 属性赋值时，这个方法会被调用。value 是新的值。
            // 在这个代码中，set 方法被重写，因为我们希望在属性被设置时触发数据绑定。
            // field = value：这行代码将属性的实际值设置为新值 value。
            // field 是 Kotlin 中的一个特殊标识符，表示属性的实际存储。
            field = value
            /**
             * 这行代码通知绑定框架属性已经改变，以便更新相关的视图。BR.userId 是自动生成的绑定资源类（BR类）中
             * userId 属性的标识符，用于标识这个属性。
             * 总之，这段代码允许 userId 属性的值在设置时触发数据绑定框架的更新，以确保相关的视图能够及时更新。
             */
            notifyPropertyChanged(BR.userId)
        }
}