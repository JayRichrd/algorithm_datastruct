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

# Subject 08-旋转数组的最小数字

**核心思想：**

- 二分法
- 将中间元素始终与high位置的元素比较，只可能存在三种情况：大于、小于、等于。
- 对于大于和等于比较好判定pivot位于前半部分还是后半部分，从而相应地忽略左边或者右边。当pivot<high时，要注意high的下一次取值为high = pivot，而不是pivot -1。这是由于这里求的是最小值，在pivot<high的情况下pivot有可能就是那个最小值，如果将pivot -1 赋值给high则会错误最小值。而对于pivot > high的情况，pivot肯定不是最小值，所以下一次low = pivot + 1。

```java
// can not assign high = pivot -1 to void the min
if (numbers[pivot] < numbers[high]) {
    high = pivot;
} else if (numbers[pivot] > numbers[high]) {
    low = pivot + 1;
}else {
    high --;
}
```

- 对于等于的情况，pivot位于前半部分和后半部分都有可能，但此时同样可以将high--来缩小范围。

**源码：**/java_pro/jv.com.cain.algorithm.search/MySearch/ subject8MinArray()

# subject 09-斐波那契数列

**核心思想：**

- 在迭代方法中，注意fn1和fn2的赋值顺序，需要先将fn1的值赋值给fn2，再将fn的值赋值给fn1

```java
// 正确示例
for (int i = 3; i <= n; i++) {
    fn = fn1 + fn2;
    fn2 = fn1;
    fn1 = fn;
}
// 错误示例
for (int i = 3; i <= n; i++) {
    fn = fn1 + fn2;
    fn1 = fn;
    fn2 = fn1;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.recursion/MyRecursion/ fibonacciSequenceByRecursive()

# subject 10-二进制中1的个数

**核心思想：**

- 最直接的方式从地位到高位，依次与2^i^(1 << i)做与(&)，判定当前位是否为1：

```java
if ((n & (1 << i)) != 0) {
    result++;
}
```

- 与(&)运算有一个特性：n & (n -1)，得到的结果就是把n低位的1转换为0。例如6(110) & (6-1) = 4(100)。那就利用这个性质依次将低位的1变成0:

```java
while (n != 0) {
    n = n & (n - 1);
    result++;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.bit/BitOperation/ subject9HammingWeight1()

# subject 11-数值的整数次方

**递归核心思想：**

- 重点在于写出递归公式：

![](https://picgo-1256537295.cos.ap-guangzhou.myqcloud.com/pictures/20220528112515.png)

```java
double result = quickPowByRecursion(x, y >> 1);
return y % 2 == 0 ? result * result : result * result * x;
```

- 并且注意判定指数的正负:

```java
return y > 0 ? quickPowByRecursion(x, y) : 1 / quickPowByRecursion(x, -y);
```

**源码：**/java_pro/jv.com.cain.algorithm.recursion/MyRecursion/ subject11MyPow()

# subject 17-打印1到最大的n位数

**递归核心思想：**

- 从高位到低位，每一位做全排列，每位上的数字取值：0~9；
- 在打印时候，数字之前的无效的0可以不必输出：

```java
for (int number : numbers) {
    // 0 before valid number should not be printed
    if (number != 0 && !flag) {
        flag = true;
    }
    if (flag) {
        System.out.print(number);
    }
}
```

**源码：**/java_pro/jv.com.cain.algorithm.recursion/MyRecursion/ subject17Print1ToMaxOfNDigits()

# subject 18-在O（1）时间删除链表结点

**核心思想：**

- 这里的待删节点是完整的节点，既有数值，也有next域；
- 常规的删除是通过操作待删节点的前一个节点，这里的思路是使用待删节点的下一个节点直接覆盖上去，也能达到删除节点的目的；

```java
if (node.next != null) {
    Node nextNode = node.next;
    node.data = nextNode.data;
    node.next = nextNode.next;
    nextNode.next = null;
}
```

- 注意边界条件：待删节点是尾节点、链表只有一个节点。

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject18DeleteNode()

# subject 21-调整数组顺序使奇数位于偶数前面

**核心思想：**

- 一前一后双指针，保证奇数在前，偶数在后；
- 前后指针往中间靠拢

**源码：**/java_pro/jv.com.cain.algorithm.array/ArrayPractice/ subject21Exchange()

# subject 22-链表中倒数第k个结点

**核心思想：**

- 双指针，看倒数第k个节点月尾节点，他们之间相差k-1;
- 那么可以假设两个快慢指针，最开始都指向第1个节点，然后快指针移动到第k个节点后再开始同事移动快慢指针，直到快指针到达尾部。

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject22GetKthFromEnd()

# subject 24-反转链表

**迭代核心思想：**

- 单链表容易丢失next域，所以在反转之需要先将next域保存下来；
- 时刻保存当前节点cur和前节点pre，反转就是将cur的next域指向pre，然后再往后滑动，更新pre和cur的值。这里需要注意更新顺序：先更新pre，在更新cur:

```java
while (cur != null) {
	Node next = cur.next;
	cur.next = pre;
	pre = cur;
	cur = next;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject24RevertLinkedList()

# subject 25-合并两个排序的链表

**迭代核心思想：**

- 每次从两个排序的链表取出元素进行比较，根据大小确定哪个元素作为新的链表元素。只有被选中的链表头结点才向后移动。

```java
while (head1 != null && head2 != null) {
    if (head1.data < head2.data) {
        cur.next = head1;
        head1 = head1.next;
    } else {
        cur.next = head2;
        head2 = head2.next;
    }
    cur = cur.next;
}
```

- 当某一个链表便利结束，直接将另一个链表放到新链表尾部即可:

```java
cur.next = head1 == null ? head2 : head1;
```

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject25MergeSortedLinkedList()

# subject 26-树的子结构

**递归核心思想：**

- 母树和子树都从根节点开始匹配，如果配上某个节点就开始树的配对。否则母树分别用左、右子树来重复与子树匹配：

```java
 // find the same node
 if (root.data == sub.data) {
     result = match(root, sub);
 }
 // continue root left subtree and right subtree
 if (!result) {
     result = subject26IsSubTree(root.left, sub);
 }
 if (!result) {
     result = subject26IsSubTree(root.right, sub);
 }
```

- 关于树的配对，也是用递归。在递归结束条件中，应该将子树判空放在最前面：

```java
// check sub firstly
if (sub == null) {
    return true;
}
if (root == null) {
    return false;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject26IsSubTree()

# subject 27-二叉树的镜像

**递归核心思想：**

- 只要要某个节点的左右子树交换了，那么剩下的就是交换当前节点的左右子树：

```java
root.left = subject27RevertTree(root.right);
root.right = subject27RevertTree(root.left);
```

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject27RevertTree()

# subject 29-顺时针打印矩阵

**核心思想：**

- 从左上角开始"画圈”便利，结束的的条件：

```java
while (2 * start < rows && 2 * start < columns) {
   ……
}
```

- 每次打印某一圈，注意各个方向的边界：

```java
// left -> right
for (int i = start; i <= endX; i++) {
    System.out.print(matrix[start][i] + ", ");
}
// top -> bottom
if (start < endY) {
    for (int i = start + 1; i <= endY; i++) {
        System.out.print(matrix[i][endX] + ", ");
    }
}
// right -> left
if (start < endX && start < endY) {
    for (int i = endX - 1; i >= start; i--) {
        System.out.print(matrix[endY][i] + ", ");
    }
}
// bottom -> top
if (start < endY - 1 && start < endX) {
    for (int i = endY - 1; i > start; i--) {
        System.out.print(matrix[i][start] + ", ");
    }
}
```

**源码：**/java_pro/jv.com.cain.algorithm.array/ArrayPractice/ subject29PrintMatrixSpiral()

# subject 30-包含min函数的栈

**核心思想:**

- 添加一个辅助栈，每次往数据栈添加一个元素的时候，都比较这元素与辅助栈顶元素大小。将小者入辅助栈，保持数据栈与辅助栈数量一样。
- 每次数据栈pop操作，辅助栈也要pop栈顶数据。
- 添加辅助栈就是为了能持续找到剩余元素中的最小值。
- 条用min函数获取最小值，只返回辅助栈顶元素，但不出栈。

**源码：**/java_pro/jv.com.cain.algorithm.stack/MinStack

# subject 31-栈的压入、弹出序列

**核心思想：**

- 输入的入栈序列，并不是一次性的入栈序列，可能是先入栈一部分，然后出栈，再入栈。只是说各个元素入栈的顺序如输入序列一样。

- 使用一个辅助栈来模拟入栈出栈操作。

- 按照给定入栈序列，每次将序列的一个元素入栈，然后将栈顶元素与出栈序列的元素比较：

  - 如果正好相同，则辅助栈就出栈，且出栈序列往后滑动一个位置。

  - 如果不相同，则继续按照入栈序列讲元素入栈到辅助栈

  ```java
  for (int element : push) {
      // push element
      helpStack.push(element);
      // depend on pop array to pop helpStack
      while (!helpStack.isEmpty() && helpStack.peek() == pop[i]) {
          helpStack.pop();
          i++;
      }
  }
  ```

- 然后根据最后辅助栈的空与否，判定有效性

```java
return helpStack.isEmpty();
```

**源码：**/java_pro/jv.com.cain.algorithm.stack/MinStack/ subject31ValidStackSequence()

# subject 32-从上往下打印二叉树

**核心思想：**

- 使用一个队列来保存每层的节点
- 一边遍历某一层，一遍遍历到的当前节点左右子节点加入到队列中
- 每次遍历某层时，队列中存储的就是当前这一层的节点数

```java
 Queue<TreeNode> assistQueue = new LinkedList<>();
assistQueue.offer(head);
while (!assistQueue.isEmpty()) {
    int curLevelSize = assistQueue.size();
    // visit every level
    for (int i = 0; i < curLevelSize; i++) {
        TreeNode node = assistQueue.poll();
        System.out.print(node.data + ", ");
        // add leaf node to assistQueue when visiting
        if (node.left != null) {
            assistQueue.offer(node.left);
        }
        if (node.right != null) {
            assistQueue.offer(node.right);
        }
    }
    System.out.println();
}
```

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject32LeveOrderVisit()

# subject 33-二叉搜索树的后序遍历序列

**递归核心思想：**

- 弄清楚二叉搜索树的概念：左子树<根节点<右边子树
- 后续遍历，最后一个节点是根节点，前半部分是左子树（比根节点小），后半部分是右子树（比根节点大）
- 拆分出左子树、右子树、根节点。然后对左子树和右子树采用相同的方法判定。
- 关键点就是pivot == right，表明本次递归按照上面的先左子树、再右子树、最后根节点拆分完成。

```java
private static boolean isBstPostVisitRecursive(int[] tree, int left, int right) {
    if (left >= right) {
        return true;
    }
    int pivot = left;
    while (tree[pivot] < tree[right]) pivot++;
    int div = pivot;
    while (tree[pivot] > tree[right]) pivot++;
    return pivot == right/*the key point*/ && isBstPostVisitRecursive(tree, left, div - 1) && isBstPostVisitRecursive(tree, div, right - 1);
}
```

 **源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject33isBstPostVisit()

# subject 34-二叉树中和为某一值的路径

**递归核心思想：**

- 注意叶子节点的判定：

```java
sumPath.offerLast(node.data);
target -= node.data;
// find leaf node and check
if (node.left == null && node.right == null && target == 0) {
    result.add(new LinkedList<>(sumPath));
    return;
}
```

- 路径使用一个双端队列保存下来，使用完后记得在最后退回当当前节点的前一个节点：

```java
sumPathDfsRecursive(node.left, target, result, sumPath);
sumPathDfsRecursive(node.right, target, result, sumPath);
// pop and continue
sumPath.pollLast();
```

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject33isBstPostVisit()

# subject 35-复制复杂链表

**核心思想：**

- 利用原来链表的节点之前的关系，将复制出来的链表节点放在对应原来链表后面，最后再根据奇偶分开两个链表：

  1.从前往后复制每个节点，并将其插入到原始对应节点后面

```java
// step1 construct node and insert behind
Node node = head;
while (node != null) {
	Node newNode = new Node();
	newNode.data = node.data;
	newNode.next = node.next;
	node.next = newNode;
	node = node.next.next;
}
```

2.然后给复制出来的节点的random赋值，同样使用的是原始节点的关系

```java
// step2 assign random
node = head;
while (node != null) {
	Node newNode = node.next;
	newNode.random = node.random == null ? null : node.random.next;
	node = node.next.next;
}
```

3.最后将奇数位置的节点和偶数位置的节点分别分离开，偶数位置分离出来的节点就是新复制的链表：

```java
// step3 divide
while (node != null) {
	Node newNode = node.next;
	node.next = node.next != null ? node.next.next : null;
	newNode.next = newNode.next != null ? newNode.next.next : null;
	node = node.next;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.linked_list/MyLinkedList/ subject35CopyLinkedList()

# subject 36-二叉搜索树与双向链表

**递归核心思想：**

- 二叉树的节点有两个指针域，正好可以当成链表节点的前后指针域。
- 中序遍历二叉搜索树正好就是排序的。
- 这里要始终记住已经转换成链表的尾结点tail，在Java中使用数组当成C++中的指针使用。

```java
private static void midConvertRecursive(TreeNode root, TreeNode[] tail) {
    if (root == null) {
        return;
    }

    if (root.left != null) {
        midConvertRecursive(root, tail);
    }

    // link root and tail node
    root.left = tail[0];
    if (tail[0] != null) {
        tail[0].right = root;
    }
    tail[0] = root;

    if (root.right != null) {
        midConvertRecursive(root, tail);
    }
}
```

- 最后再从尾结点开始倒序遍历，并可以得到头节点：

```java
TreeNode head = tail[0];
while (head.left != null) {
    head = head.left;
}
```

**源码：**/java_pro/jv.com.cain.algorithm.tree/MyTree/ subject36Bst2LinkedList()

# 参考

- 《键指offer》