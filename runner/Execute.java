package runner;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class Execute {
	public String execute(String code, String param){
		// TODO Auto-generated method stub
				String result="";
				char[] codes = code.toCharArray();
				char[] input = param.toCharArray();
				ArrayList<Integer> jumpover = new ArrayList<Integer>();
				ArrayList<Integer> jumpback = new ArrayList<Integer>();
				int ptr=0;
				int inputptr=0;
				int stackIndex=0;
				int inputLength=0;
				int jumpoverIndex=0;
				int jumpbackIndex=0;
				boolean isRight=true;
				
				for(int i=0;i<code.length();i++){
					if(codes[i]=='['){
						jumpover.add(i);
						jumpoverIndex+=1;
					}else if(codes[i]==']'){
						if(jumpbackIndex==jumpoverIndex-1){
							jumpback.add(i);
							jumpbackIndex+=1;
						}else{
							isRight=false;
							result="exm???";
						}
					}else if(codes[i]=='>'){
						stackIndex+=1;
					}
				}

				char[] content = new char[stackIndex+1];
				stackIndex=0;
				if(isRight){
				try{
					for(int i=0;i<code.length();i++){
					
					switch(codes[i]){
					case'<':
						stackIndex-=1;
						ptr-=1;
						break;  
					case'>': 
						stackIndex+=1;
						ptr+=1;
						break;
					case'+':  
						content[ptr]+=1;
						break;  
					case'-': 
						content[ptr]-=1;
						break;
					case',':  
						inputLength+=1;
						content[ptr]=input[inputptr];
						inputptr+=1;
						break;  
					case'.': 
						result=result+content[ptr];
						break;
					case'[':  
						if(content[ptr]==0){
							int temp = jumpover.indexOf(i);
							int stepInto = jumpback.get(temp);
							i=stepInto;
						}
						break;  
					case']': 
						if(content[ptr]!=0){
							int temp = jumpback.indexOf(i);
							int stepInto = jumpover.get(temp);
							i=stepInto;
						}
						break;
					}
					if(stackIndex<0){
						result="������ƶ�����ָ�룿����";/*�˴�����*/
					}
				  }
					if(inputLength!=param.length()){
						result="�����ô��������ȴ���䳤һ�㣿����";/*�˴�����*/
					}else if(jumpoverIndex!=jumpbackIndex){
						result=("дǰ���Ų�д�����ŵ�sb");/*�˴�����*/
					}
				}catch(Exception e){
					
				}}
						
				return result;
	}
}
