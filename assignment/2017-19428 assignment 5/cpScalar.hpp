//
// Created by Owner on 2018-06-10.
//

#ifndef UNTITLED2_CPSCALAR_HPP
#define UNTITLED2_CPSCALAR_HPP

#include <iostream>

using namespace std;

class cpScalar
{
    private:
    double _num;

    public:
    cpScalar(int num)
    { _num = (double)num;}

    cpScalar(double num)
    { _num = num; }
    cpScalar() // default constructor
    { _num = 0;}
    double getnum() const
    { return _num;}

    friend ostream &operator<<(ostream &sout, const cpScalar &r)
    { sout <<  ((r.getnum() / 1) == 0 ? (int)r.getnum() : r.getnum()); return sout;}

    cpScalar operator+(const cpScalar& num1)
    { return cpScalar(num1.getnum() + _num);}

    cpScalar operator-(const cpScalar& num1)
    { return cpScalar(_num - num1.getnum());}

    cpScalar operator*(const cpScalar& num1)
    { return cpScalar(num1.getnum() * _num);}

    cpScalar operator/(const cpScalar& num1)
    { return cpScalar(_num / num1. getnum());}
};


#endif //UNTITLED2_CPSCALAR_HPP
