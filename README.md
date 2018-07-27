
Google 出的一套组件库（简称为 AAC）的 demo。
参考这篇教程写出来的 [理解Android Architecture Components系列](https://www.jianshu.com/p/42eb71ec4a19)

## 为什么写这个
Google 官方的 [Samples](https://github.com/googlesamples/android-architecture-components) 写的比较规范、全面，但是可能对新手难以理解，所以写了一个简化版的

## Demo 效果
在 Activity 里的 EditText 中输入 GitHub 用户名，则立刻在下面的显示区域（Fragment）中显示用户信息。
屏幕旋转、配置改变均不会丢失数据。

## 关键技术点
- Room：基于 SQL 的持久化库（需掌握基础的 SQL 语句）
- LiveData：一个**具有生命周期感知能力**的持有数据的可观测的类（类似于Rxjava 中的 Observable，但是你无需在生命周期中去 `subscribe()` 或者 `unSubscribe()`）
- ViewModel：在生命周期中，用于存储和管理界面相关的数据。最大的特点是即使屏幕旋转配置改变也不会丢失数据。

## 使用 AAC 后的架构
![AAC 架构](https://upload-images.jianshu.io/upload_images/3067882-edccc85dc787af85.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/700)

对比一下 MVP 架构：

![MVP 架构](https://github.com/googlesamples/android-architecture/wiki/images/mvp.png)
