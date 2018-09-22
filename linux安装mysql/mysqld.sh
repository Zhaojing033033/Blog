basedir=/usr/local/mysql
cd /usr/local/
#下载解压
wget https://cdn.mysql.com//Downloads/MySQL-5.7/mysql-5.7.23-linux-glibc2.12-x86_64.tar.gz
#解压
tar -zxvf mysql-5.7.23-linux-glibc2.12-x86_64.tar.gz
#重命名
mv mysql-5.7.23-linux-glibc2.12-x86_64   mysql
#创建用户和用户组
groupadd mysql
useradd mysql -g mysql
#创建数据目录，并且修改属主
cd mysql 
mkdir data
chown -R mysql.mysql  /usr/local/mysql
#执行安装命令,命令没有换行
bin/mysqld --user=mysql --datadir=/usr/local/mysql/data --basedir=/usr/local/mysql --initialize
#修改配置 
#sed -i -E "s#(datadir=).*\$#\1$basedir\/data#g" /etc/my.conf
#sed -i -E "s#(socket=).*#\1$basedir\/mysql.sock#g" /etc/my.conf
#echo "[client]">>/etc/my.conf
#echo "socket=$basedir/mysql.sock"

#直接用拷贝的,需要将my.conf与该文件的放置在同一目录
cat my.conf >>/etc/my.conf
#创建软连接，可以使用service mysql start/stop/restart进行服务管理
ln -s /usr/local/mysql/support-files/mysql.server /etc/init.d/mysql
#创建个软连接，可以任何目录使用mysql的命令
ln -s /usr/local/mysql/bin/mysql /usr/bin
#删除mysql的压缩包
rm -f /usr/local/mysql-5.7.23-linux-glibc2.12-x86_64.tar.gz


