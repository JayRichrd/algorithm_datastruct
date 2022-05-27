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

- 函数参数使用==引用==，避免值复制，效率更高；

- 注意检查是否是当前对象复制给当前对象，如果是就直接返回当前对象即可；

- 边界条件考虑：确保this对象新分配内存成功并成功把other的数据复制给this对象，然后再删除this对象的内存占用。这里使用==临时变量==(在作用区域结束后会自动被析构)来做中转，从而把other的数据复制给this，并确保this之前的数据被删除；

- 将当前对象作为==引用==返回，一是为了能够连续复制s1 = s2 = s3，二是引用没有值复制，效率更高；


**源码：**/cpp_pro/override_operator_assign

# subject 03-二维数组查找

**核心思想：**

取出==左下角(右上角)==的数与目标数比较查找：

- 要查找的数=左下角的数，则存在；
- 要查找的数<左下角的数，则去掉当前所在的一行，继续从剩下的数组中的左下角开始查找；
- 要查找的数>左下角的数，则去掉当前所在列，继续从剩下的数组中的左下角开始查找。

```java
while (row >= 0 && column < columns) {
    int cur = matrix[row][column];
    if (cur == num) {
        return true;
    } else if (cur < num) {
        column++;
    } else {
        row--;
    }
}
```

取左下角，可以理解为从上到下从左边到右连接起来的话，左下角正好是二分查找的中间点。这样每次比较都能移动一行或者一列。而取左上角或者右下角则不行。

**源码：**/java_pro/jv.com.cain.algorithm.search/MySearch/ subject3Find()

# subject 04-替换字符串

**核心思想：**

- 从空格变成%20，字符串容量扩大了3倍，所以初始就分配一个3倍原字符串长度的空间
- 从头开始遍历原始字符串，并将内容拷贝到新分配的空间中，同时将空格替换成%20.

**源码：**/java_pro/jv.com.cain.algorithm.string/MyString/ subject4ReplaceSpace()

# subject 05-从尾到头打印链表

**核心思想：**

- 栈思想或者递归思想

ps:栈和递归之间有着密切联系

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject5PrintRevertLinkedList() subject5PrintRevertLinkedListByRecursion()

# subject 06-重建二叉树

 **迭代核心思想：**

- 前序遍历的第一节点就是根节点，用找到的这个根节点在中序遍历中找出左子树和右子树。
- 前序遍历根节点后面先是左子树，再是右子树。
- 找到左右子树后又回到问题本身重建二叉树。

> ps:可以从中序遍历+前序/后序遍历重建出唯一的一颗树

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ buildBinaryTree()

# subject 07-用两个栈实现队列

**核心思想：**

- 用一个栈inStack，每次入队列都压栈进入；
- 用一个栈outStack，每次出队都从当中弹出，如果没有就将inStack的全部元素“倒”到outStack中来：

```java
if (outStack.isEmpty()) {
    if (inStack.isEmpty()) {
        return null;
    }
    while (!inStack.isEmpty()) {
        outStack.push(inStack.pop());
    }
}
```

**源码：**/java_pro/jv.com.cain.algorithm.queue/QueueByStack

# 参考

- 《键指offer》