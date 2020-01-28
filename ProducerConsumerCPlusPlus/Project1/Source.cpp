#include <iostream> 
#include <thread> 
using namespace std;

void producer(int &pid, int &cid, int total)
{
	int &p = pid;
	int &c = cid;

	while (p < total) {
		while (p != c) {
			cout << "Waiting for request" << endl;
		}
		cout << "making burger: " << p << endl;
		p = p + 1;
	}

	cout << "finished making burgers" << endl;
}

void consumer(int& pid, int& cid, int total)
{
	int& p = pid;
	int& c = cid;

	while (c < total) {
		while (p <= c) {
			cout << "waiting for burger" << endl;
		}
		cout << "eating burger: " << c << endl;
		c = c + 1;
	}
	cout << "finished eating" << endl;
}

int main()
{
	int p = 0;
	int c = 0;
	int total = 10;

	thread th1(producer, ref(p), ref(c), total);
	thread th2(consumer, ref(p), ref(c), total);

	th1.join();
	th2.join();

	return 0;
}