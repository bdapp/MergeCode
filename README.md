# MergeCode
用于申请软著时生成项目源码文档


查找程序的总行数 （查找某目录下.java .xml文件的总行数）
```
find . "(" -name "*.java" -or -name "*.xml" ")" -print | xargs wc -l 

```
