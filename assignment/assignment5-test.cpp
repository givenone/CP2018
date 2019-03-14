#include <algorithm>
#include <iostream>
#include <vector>
#include "cpScalar.hpp"
#include "cpVector.hpp"

int main(int argc, char *argv[])
{
	cpScalar s(3);
	cpScalar t(4);
	std::cout << s << std::endl; //Should print 3.
	std::cout << s + t << std::endl; //Should print 7.

	/* Generate an cpScalar array with the vector. */
	std::vector<cpScalar> vv;
	vv.push_back(s);
	vv.push_back(t);
	cpScalar a[2];
	std::copy(vv.begin(), vv.end(), a);
	cpVector v(a, 2);
	std::cout << s * v << std::endl; //Should print [9, 12].
	std::cout << v + t << std::endl; //Should print [7, 8].

	return 0;
}
