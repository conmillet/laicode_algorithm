package laicode_java;

//a To the power of b
public class Solution7 {
	//复制java文件
	//for i in {3..100}; do cp Solution2.java "Solution$i.java"; done
	//分离某一行然后替换
	//for i in {3..100}; do head -3 "Solution$i.java" | tail -1 $1 | awk '{split($0, a, ' '); print a[3]' ; done
	//sed -i ' ' 's/hi/hello/' file1
	//更改后复制到新文件
	//awk 'NR==2{gsub("hi", "Hello", $1)}; {print $0}' file1 > newfile
	//代变量就必须要用“”双引号
	//for i in {3..100}; do sed -i " " "3s/Solution/Solution$i/" Solution$i.java ; done
	public long power(int a, int b) {
		if (b==0) {
			return 1;
		}
		if (a==0) {
			return 0;
		}
		long half=power(a, b/2);
		return b%2==0?half*half : half*half*a;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Solution7 s7 = new Solution7();
		long result7 = s7.power(1,101);
		System.out.println(result7);
		return;
	}
}
