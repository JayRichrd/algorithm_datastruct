package jv.com.cain.algorithm.string;

@SuppressWarnings("JavaDoc")
public class MyString {
    public static void main(String[] args) {
        System.out.println("==========test Subject4 ReplaceSpace==========");
        testSubject4ReplaceSpace();
    }

    /**
     * refe: https://leetcode.cn/problems/ti-huan-kong-ge-lcof/solution/mian-shi-ti-05-ti-huan-kong-ge-by-leetcode-solutio/ method1
     * Time complexity: O(n)
     * Spatial complexity: O(n)
     *
     * @param str
     * @return
     */
    public static String subject4ReplaceSpace(String str) {
        if (str.isEmpty()) {
            return str;
        }
        int length = str.length();
        char[] strArray = new char[length * 3];
        int capacity = 0;
        for (int i = 0; i < length; i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                strArray[capacity++] = '%';
                strArray[capacity++] = '2';
                strArray[capacity++] = '0';
            } else {
                strArray[capacity++] = c;
            }
        }
        return new String(strArray, 0, capacity);
    }

    public static void testSubject4ReplaceSpace() {
        String str = "we are happy.";
        System.out.println("replaced str: " + subject4ReplaceSpace(str));
    }
}
