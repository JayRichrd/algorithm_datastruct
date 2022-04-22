# subject 01-方法重载

**方法重载**

核心代码：

```c++
CMyString &CMyString::operator=(const CMyString &other/*①*/) {
    std::cout << "call override assign function. other(" << &other << ") data: " << cMyString.p_mData << ", this("
              << this << ") data: "
              << this->p_mData << std::endl;
    /**
     * if the same instance, just return it.
     */
    if (this != &other) {/*②*/
      	/**
      	*③
      	**/
        CMyString temp_cMyString(other);
        char *temp_pData = temp_cMyString.p_mData;
        temp_cMyString.p_mData = p_mData;
        p_mData = temp_pData;
    }
  	/*④*/
    return *this;
}
```

核心知识点：

①函数参数使用==引用==，避免值复制，效率更高；

②注意检查是否是当前对象复制给当前对象，如果是就直接返回当前对象即可；

③边界条件考虑：确保this对象新分配内存成功并成功把other的数据复制给this对象，然后再删除this对象的内存占用。这里使用==临时变量==(在作用区域结束后会自动被析构)来做中转，从而把other的数据复制给this，并确保this之前的数据被删除；

④将当前对象作为==引用==返回，一是为了能够连续复制s1 = s2 = s3，二是引用没有值复制，效率更高；

[源码](/cpp_pro/override_operator_assign)



# 参考

- 《键指offer》