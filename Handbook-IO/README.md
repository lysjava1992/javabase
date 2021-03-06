

  **程序空间** ：分配给用户程序（java）的空间
  **内核空间** ：内核拥有的内存空间

>  用户进程（java程序）是无法直接操作键盘，磁盘，网卡等硬件设备的，它只能通过系统调用由**用户态**切换为**内核态**来调用;

  数据的移动是 ：

    1. 用户进程发起调用由 用户态——>内核态
    2. 内核负责从硬件中读取数据到内核缓冲区 ——>第一次复制
    3. 内核态——>用户态
    4. 将内核缓冲区数据复制到用户缓冲区——>第二次复制
      读取完毕，若要再发送到网络上：
    5. 用户态——>内核态
    6. 将数据从用户缓存区复制到内核（socket）缓存区——>第三次复制
    7. 操作系统将数据从Socket缓存区复制到网卡缓存，然后将其通过网络发出——第四次复制



###Java IO

​       java中的io流是实现输入/输出的基础，它可以方便的实现数据的读写操作，在java中把不同的输入/输出源抽象表述为 "流"**流是一组有顺序的字节集合，是对数据传输的抽象或总称**

> java中的IO可以理解为是java程序和操作系统内核两个对象之间进行的
> 而io中的阻塞非阻塞/同步异步都是指这两者相互作用的激活

 ![io](/img/io.png)

  ### IO分类

**数据流向**

   针对进程自己

- ​    输出流 ：Out
- ​    输入流： In

**数据格式**

- ​     字节流：  二进制数据 例如音乐 图片 视频 等等文件
- ​     字符流： （Unicode字符）文本数据  java采用Unicode编码，在操作汉字 国际化方面具有优势

**包装过程**

- ​    节点流：低级流，直接负责读写
- ​    处理流：高级流，处理节点流（低级流）


### 网络IO
 在文件操作中 inputStream.read()== -1 来判断文件是否读取完毕但在网络IO中并不适用
 网络IO中通信的两端若都在连接中 则inputStream.read()会阻塞在此，解决方案：
- bufferReader.readLine()==null 可以用来判断
- 双方约定结束符 来做判断

 

