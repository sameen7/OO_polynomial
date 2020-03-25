package add;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class add {
	public static void main(String[] args){
		String[] pol = new String[100];
		String[] op = new String[100];
		int[] num1 = new int[1000];
		int[] pow1 = new int[1000];
		int[] num2 = new int[1000];
		int[] pow2 = new int[1000];
		int i = 0, j = 0, k = 0, l = 0, n, flag, h, g, pos, t1, t2;
		boolean ok = true;
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		String str = input.replace(" ", "").replace("\t", "");
		if(!str.isEmpty()){
			if(isEnglish(str)){
				if(check(str)){
					Pattern p = Pattern.compile("(?<=\\{)(.+?)(?=\\})");
					Matcher m = p.matcher(str);
					while(m.find()){
						pol[i++] = m.group();
					}
					Pattern pp = Pattern.compile("(?<=\\})(.+?)(?=\\{)");
					Matcher mm = pp.matcher(str);
					while(mm.find()){
						op[k++] = mm.group();
					}
					String s = pol[0];
					Pattern p1 = Pattern.compile("(?<=\\()(.+?)(?=\\))");
					Matcher m1 = p1.matcher(s);
					while(m1.find()){
						Pattern p2 = Pattern.compile(".*(?=\\,)");
						Matcher m2 = p2.matcher(m1.group());
						Pattern p3 = Pattern.compile("(?<=\\,).*");
						Matcher m3 = p3.matcher(m1.group());
						if(m2.find()){
							if(str.charAt(0) == '-'){
								num1[j++] = 0 - Integer.parseInt(m2.group());
							}else{
								num1[j++] = Integer.parseInt(m2.group());
							}
						}
						if(m3.find()){
							pow1[j-1] = Integer.parseInt(m3.group());
						}
					}
					if(isSame(pow1, j)){
						ok = false;
					}
					for(n = 1; n < i; n++){
						String ss = pol[n];
						Pattern pp1 = Pattern.compile("(?<=\\()(.+?)(?=\\))");
						Matcher mm1 = pp1.matcher(ss);
						while(mm1.find()){
							Pattern pp2 = Pattern.compile(".*(?=\\,)");
							Matcher mm2 = pp2.matcher(mm1.group());
							Pattern pp3 = Pattern.compile("(?<=\\,).*");
							Matcher mm3 = pp3.matcher(mm1.group());
							if(mm2.find()){
								num2[l++] = Integer.parseInt(mm2.group());
							}
							if(mm3.find()){
								pow2[l-1] = Integer.parseInt(mm3.group());
							}
						}
						if(isSame(pow2, l)){
							ok = false;
						}
						for(g = 0; g < l; g++){
							flag = 0;
							for(h = 0; h < j; h++){
								if(pow1[h] == pow2[g]){
									flag = 1;
									if(op[n-1].charAt(0) == '+'){
										num1[h]+=num2[g];
									}else{
										num1[h]-=num2[g];
									}
								}
							}
							if(flag == 0){
								if(op[n-1].charAt(0) == '+'){
									num1[j++] = num2[g];
									pow1[j-1] = pow2[g];
								}else{
									num1[j++] = 0 - num2[g];
									pow1[j-1] = pow2[g];
								}
							}
						}
						l = 0;
					}
					h = j;
					for(g = 0; g < h; g++){
						pos=g;
				        for(l=g+1;l<h;l++){
				            if(pow1[l]<pow1[pos]){
				                pos=l;
				            }
				        }
				        if(pos!=g){
				            t1=pow1[pos];
				            pow1[pos]=pow1[g];
				            pow1[g]=t1;
				            t2=num1[pos];
				            num1[pos]=num1[g];
				            num1[g]=t2;
				        }
					}
					if(ok){
						System.out.print("{");
						for(l = 0; l < j; l++){
							if(l == j - 1){
								System.out.print("(" + num1[l] + "," + pow1[l] + ")" + "}");
							}else{
								System.out.print("(" + num1[l] + "," + pow1[l] + ")" + ",");
							}
						}
					}else{
						System.out.println("warning!:出现同一多项式中有相同指数的单项式的情况");
					}
				}
			}else{
				System.out.print("warning!:请不要输入汉字");
			}
		}else{
			System.out.print("warning!:输入不能为空");
		}
	}

	static boolean check(String str){
		String poly;
		if(str.charAt(0) != '-' && str.charAt(0) == '{'){
			poly = '+' + str;
		}else{
			poly = str;
		}
		Pattern pat = Pattern.compile("([+--]\\{[^\\}]+}){0,20}");
		Matcher mat = pat.matcher(poly);
		if(mat.matches()){
			Pattern pat2 = Pattern.compile("(\\{[^\\}]+})");
			Pattern pat3 = Pattern.compile("\\{\\([+--]?\\d{1,6},[+--]?\\d{1,6}\\)(,\\([+--]?\\d{1,6},[+--]?\\d{1,6}\\)){0,49}\\}");
			Matcher mat2 = pat2.matcher(poly);
			while(mat2.find()){
				Matcher mat3 = pat3.matcher(mat2.group(1));
				if(mat3.matches() == false){
					System.out.println("warning!:输入不合法");
					return false;
				}
			}
			return true;
		}else{
			System.out.println("warning!:输入不合法");
			return false;
		}
	}
	
	static boolean isSame(int[] arr, int num){
		for(int i = 0; i < num; i++){
			for(int j = i + 1; j < num; j++){
				if(arr[i] == arr[j]){
					return true;
				}
			}
		}
		return false;
	}
	
	static boolean isEnglish(String str){
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+");
		Matcher m = p.matcher(str);
		if(m.matches()){
			return false;
		}else{
			return true;
		}
	}
}
