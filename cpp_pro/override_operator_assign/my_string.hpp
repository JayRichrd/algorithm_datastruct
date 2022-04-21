//
// Created by cainjiang on 2022/4/20.
//

#ifndef ALGORITHM_PRACTICE_MY_STRING_HPP
#define ALGORITHM_PRACTICE_MY_STRING_HPP

class CMyString {
private:
    char *p_mData;
public:
    CMyString();

    explicit CMyString(char *data = nullptr);

    CMyString(const CMyString &other);

    ~CMyString();

    /**
     * override operator assign
     * @param other the data witch use to assign this
     * @return return this
     */
    CMyString &operator=(const CMyString &other);

    char *getData();
};

#endif //ALGORITHM_PRACTICE_MY_STRING_HPP
