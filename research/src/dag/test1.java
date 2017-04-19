package dag;

import java.util.*;


public class test1 {
	public static int max(int a[])    //��һ���������ֵ������
	{
     int m = 0;
	 for(int i = 0;i<a.length;i++) 
	 {
	   if(a[i]>=a[0])
	     m = i;
	 }	
     return m;
	}
	public static int min(int a[])    //��һ���������ֵ������
	{
     int m = 0;
	 for(int i = 0;i<a.length;i++) 
	 {
	   if(a[i]<=a[0])
	     m = i;
	 }	
     return m;
	}
		
    public static void main(String[] args){
    int pe[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};	         //��ʾ����ʼʱ��
    int time[] = {0,0,0};                                //������ʼʱ��
    int dispatched[] = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};	 //����������Դ���1-3
    int b_level[] = {0,1,1,2,3,3,3,4,4,4};  //10�������b_level
    int t[] = {2,3,3,4,2,3,3,3,1,4};
    int qiandao[][] = new int[10][10];  //a[2][1]=1��ʾ����2������1֮ǰִ��
    qiandao[2][1] = 1;
    qiandao[3][1] = 1;
    qiandao[4][1] = 1;
    qiandao[5][1] = 1;
    qiandao[7][1] = 1;
    qiandao[6][2] = 1;
    qiandao[7][2] = 1;
    qiandao[8][3] = 1;
    qiandao[8][4] = 1;
    qiandao[9][6] = 1;
    qiandao[9][7] = 1;
    qiandao[9][8] = 1;
    for(int i = 0;i<10;i++)     
    {
     int maxid = max(b_level);    //ѡ����b_levelֵ 
     b_level[maxid] = 0;          
     if(maxid==1){
       pe[maxid] = 0;
       dispatched[maxid]=0;	 
       time[0]+=t[maxid];	 
     }
     else
     {
        int early[4]={-1,-1,-1,-1};//��¼һ������������䵽0-3�Ż����ܹ�����ִ�е�ʱ��
        int a[4]={-1,-1,-1,-1};//��¼�ɸ������ڵ�����Ŀ�ʼʱ�����ƣ�����ͬ������ͨ��ʱ��������ͬ������ͨ��ʱ�������    
        for(int machid=0;machid<4;machid++)//��4̨�����Ϸ�
    	{
    	int j=1;
    	a[0]=-1;
    	a[1]=-1;
        a[2]=-1;
        a[3]=-1;
        //��¼�ɸ������ڵ�����Ŀ�ʼʱ�����ƣ�����ͬ������ͨ��ʱ��������ͬ������ͨ��ʱ�������
        //----------------------------------------------------trymap---------------------------------
        a[0]=time[machid];//��¼��ǰ�����ĵ�ǰʱ�䣬������񱻷��䵱ǰ��������������ڸ�ʱ���Ժ�ִ��
        for(int i=1;i<10;i++)//���� �ҵ�maxid�ڵ��ǰ��
    	{
    	  if(node[i][maxid]!=0)//�ҵ�һ��ǰ����node[i][maxid]��ֵΪ�ڵ�maxid��ڵ�i��ͨ��ʱ��
    	  {
    	  if(tdispatched[i]==machid)//���ǰ���ڵ�i��ýڵ�maxid�����ֵ���ͬһ������
    	  {
    	   a[j]=time[machid];//maxid����ʼʱ������Ÿû����ĵ�ǰʱ��
    	   j++;
    	  }else
    	  {                                   //���ǰ���ڵ���ýڵ�û�з��䵽ͬһ������
    	   a[j]=pe[i]+t[i]+node[i][maxid];//��maxid����ʼʱ��Ϊǰ������ڵ����ʱ��+��֮ͨ�ŵ�ʱ��
    	   j++;
    	  }
    		 }
    		 }//for��ɺ�jΪ maxid �ڵ� ǰ���ĸ�����a[0....(j-1)]Ϊ��Ӧǰ���ڵ�����Ŀ�ʼʱ�䣬��������Ϊ���������
    		 early[machid]=a[max(a)];
    		 }   //�õ�early[0...3],������С��Ϊѡ�еķ��䷽������ʼʱ�����磩
    		 int fangan=min(early);//��¼Ӧ�÷��䵽��̨��������ʼʱ�����磩
    		 dispatched[maxid]=fangan;//tdispatched[maxid]��¼maxid���񱻷��䵽��̨����
    		 pe[maxid]=early[fangan];  //pe[maxid]��¼maxid����Ŀ�ʼʱ��
    		 time[fangan]=pe[maxid]+t[maxid];
        }
    }
    } 
}

