//
// Created by Owner on 2018-06-10.
//

#ifndef UNTITLED2_CPVECTOR_HPP
#define UNTITLED2_CPVECTOR_HPP

#include "cpScalar.hpp"

class cpVector{

    private:
    cpScalar* _value;
    int _size;

    public:

    cpVector(const cpScalar arr[], unsigned int size)
    {
        _size = size;
        _value = new cpScalar[_size];
        for(int i=0; i<size; i++)
            _value[i] = arr[i];
    }

    cpVector() // default
    { _size = 0; }

    cpScalar* getvalue() const
    { return _value;}

    friend ostream &operator<<(ostream &sout, const cpVector &r)
    {
        sout << "[";
        for(int i=0; i<r._size; i++){
            if(i == 0)
                sout << r.getvalue()[i].getnum();
            else
                sout << ", " << r.getvalue()[i].getnum();
        }
        sout << "]";
        return sout;
    }

    // vector + vector
    cpVector operator+(const cpVector & other)
    {
        if(_size != other._size)
            return cpVector();
        cpScalar temp[_size];
        for(int i=0; i<_size; i++)
            temp[i] = cpScalar(_value[i].getnum() + other._value[i].getnum());
        return cpVector(temp, _size);
    }

    cpVector operator-(const cpVector & other)
    {
        if(_size != other._size)
            return cpVector();
        cpScalar temp[_size];
        for(int i=0; i<_size; i++)
            temp[i] = cpScalar(_value[i].getnum() - other._value[i].getnum());
        return cpVector(temp, _size);
    }

    double operator*(const cpVector & other)
    {
        if(_size != other._size)
            return 0;
        double ans = 0;
        for(int i=0; i<_size ; i++)
            ans += _value[i].getnum() * other._value[i].getnum();
        return ans;
    }

    // scalar + vector

    friend cpVector operator+(const cpScalar& a, const cpVector& b)
    {
        cpScalar temp[b._size];
        for(int i=0; i<b._size ; i++)
            temp[i] = cpScalar(b._value[i].getnum() + a.getnum());
        return cpVector(temp, b._size);
    }
    friend cpVector operator-(const cpScalar& a, const cpVector& b)
    {
        cpScalar temp[b._size];
        for(int i=0; i<b._size ; i++)
            temp[i] = cpScalar( a.getnum()- b._value[i].getnum() );
        return cpVector(temp, b._size);
    }
    friend cpVector operator*(const cpScalar& a, const cpVector& b)
    {
        cpScalar temp[b._size];
        for(int i=0; i<b._size ; i++)
            temp[i] = cpScalar(b._value[i].getnum() * a.getnum());
        return cpVector(temp, b._size);
    }


    // vector + scalar

    cpVector operator+(const cpScalar& b)
    {
        cpScalar temp[_size];
        for(int i=0; i<_size ; i++)
            temp[i] = cpScalar(_value[i].getnum() + b.getnum());
        return cpVector(temp, _size);
    }
    cpVector operator-(const cpScalar& b)
    {
        cpScalar temp[_size];
        for(int i=0; i<_size ; i++)
            temp[i] = cpScalar(_value[i].getnum() - b.getnum());
        return cpVector(temp, _size);
    }
    cpVector operator*(const cpScalar& b)
    {
        cpScalar temp[_size];
        for(int i=0; i<_size ; i++)
            temp[i] = cpScalar(_value[i].getnum() * b.getnum());
        return cpVector(temp, _size);
    }
    cpVector operator/(const cpScalar& b)
    {
        cpScalar temp[_size];
        for(int i=0; i<_size ; i++)
            temp[i] = cpScalar(_value[i].getnum() / b.getnum());
        return cpVector(temp, _size);
    }
};

#endif //UNTITLED2_CPVECTOR_HPP
