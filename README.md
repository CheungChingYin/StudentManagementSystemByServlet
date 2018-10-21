## 一、项目简介
这一个项目是学生管理系统的Servlet版，后端采用`Servlet`，前端使用`Vue`、`BootStrap`等框架，数据库使用MySQL实现。
项目中的个别实现过程可以查看我的博客的文章[《基于Servlet的学生管理系统》](https://blog.csdn.net/qq_33596978/article/details/83188529)进行查看。

我把此项目部署到了我的服务器中，各位同学可以在线体验一下这个项目：[点我体验](http://139.199.66.197/StudentManagementSystemByServlet/Login)
由于超级管理员能够删除其他管理员账号，故这里只能给出普通管理员账号供大家使用，超级管理员和普通管理员的差别是普通管理员少了一个管理员管理模块。
- 用户名：李白
- 密码：test1234
<br><br>
## 二、如何安装？（以Eclipse为例，推荐使用MyEclipse）
### 1.打开Eclipse，点击`File`->`import`
![iBtu28.png](https://s1.ax1x.com/2018/10/21/iBtu28.png)
### 2.选中`Git`下的`Project from Git`
![iBtKxS.png](https://s1.ax1x.com/2018/10/21/iBtKxS.png)
### 3.复制我这个项目的Git地址
[![iBtlrQ.md.png](https://s1.ax1x.com/2018/10/21/iBtlrQ.md.png)](https://imgchr.com/i/iBtlrQ)
### 4.回到Eclipse，选择`clone URI`
![iBtgG6.png](https://s1.ax1x.com/2018/10/21/iBtgG6.png)
### 5.在`URI`一栏粘贴刚刚复制的地址（有时候会Eclipse自动识别你复制的地址）
Authentication那两列是用于识别是否是本仓库的作者，以便修改Commit，所以你们不需要填入
![iBt2RK.png](https://s1.ax1x.com/2018/10/21/iBt2RK.png)
### 6.到达这一步会选择分支（Branch），可以直接next，不需要license的同学可以只勾选`master`
![iBt4qH.png](https://s1.ax1x.com/2018/10/21/iBt4qH.png)
### 7.`Directory`是指把项目存储在哪里，自己选择一个适合的地方存储即可
![iBtqRf.png](https://s1.ax1x.com/2018/10/21/iBtqRf.png)
### 8.下一步即可
![iBtXQS.png](https://s1.ax1x.com/2018/10/21/iBtXQS.png)
### 9.点击`Finish`
![iBtjsg.png](https://s1.ax1x.com/2018/10/21/iBtjsg.png)
### 10.导入后会发现有错误，先不管，导入了数据库再说
在项目下有一个叫`MySQL`的文件夹，里面有一个名为`Student20181019.sql`的数据库脚本
可以借助`Navicat`,或者`MySQL WorkBench CE`进行导入（此处我使用的是`Navicat`）
在连接好数据库的时候，右键数据库,点击`运行SQL文件即可`
![iBNoXF.png](https://s1.ax1x.com/2018/10/21/iBNoXF.png)
### 11.`文件`一栏只要找到你项目下的`Student20181019.sql`数据库脚本，点击开始即可导入数据库
![iBNLkR.png](https://s1.ax1x.com/2018/10/21/iBNLkR.png)
### 12.回到Eclipse，右键项目，点击`build path`->`Configure Build Path`
[![iBNvp6.md.png](https://s1.ax1x.com/2018/10/21/iBNvp6.md.png)](https://imgchr.com/i/iBNvp6)
### 13.选择`Libraries`,按如图操作
![iBUP7d.png](https://s1.ax1x.com/2018/10/21/iBUP7d.png)
### 14.选择`Tomcat8.5`
如果没有是因为你的Eclipse没有导入Tomcat服务器，需要先导入服务器
![iBUmjS.png](https://s1.ax1x.com/2018/10/21/iBUmjS.png)
### 15.在项目的src下有一个名为`db.properties`的文件
修改里面的`jdbc.user`和`jdbc.password`为自己数据库的用户名和密码，保存即可
![iBUtjU.png](https://s1.ax1x.com/2018/10/21/iBUtjU.png)
### 16.Eclipse下面，选择`Server`栏，右键`Tomcat`服务器，选择`Add And Remove`
[![iBUdHJ.md.png](https://s1.ax1x.com/2018/10/21/iBUdHJ.md.png)](https://imgchr.com/i/iBUdHJ)
### 17.在左侧选择项目，点击`Add`，项目会自动添加到右边，点击`Finish`即可
![iBUBNR.png](https://s1.ax1x.com/2018/10/21/iBUBNR.png)
### 18.右键Tomcat服务器，选择`Start`即可
[![iBUD41.md.png](https://s1.ax1x.com/2018/10/21/iBUD41.md.png)](https://imgchr.com/i/iBUD41)
### 19.Tomcat启动后，浏览器访问 http://localhost:8080/StudentManagementSystemByServlet/Login 即可完成
- 普通管理员
- 用户名：李白
- 密码：test1234
- 超级管理员
- 用户名:张三
- 密码：abc123456

[![iBU4UA.md.png](https://s1.ax1x.com/2018/10/21/iBU4UA.md.png)](https://imgchr.com/i/iBU4UA)
