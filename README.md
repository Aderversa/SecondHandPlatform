安装并启动Docker，之后clone本项目到本地
用cmd打开本项目的根目录，然后执行以下命令：

```shell
cd package
./install.sh
```
执行完上述语句之后哦，docker会自动启动服务器，并持久化MySQL的数据到volums目录下的mysql-volum/data中

我录制了视频发在B站上了，可以去参考一下，当然如果你们有更好的部署方案也可以提出来

链接：https://www.bilibili.com/video/BV1yD421T74h/?vd_source=5f9e02c20d11854aaefd0fc6c6855c12

**启动时有一个注意事项就是如果`volums/mysql-volum/data`中有数据请将里面的文件清空，否则很大概率不能启动**
