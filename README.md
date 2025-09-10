import java.io.*;
import java.util.*;

public class prueb {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(new File("entrada.txt"));
        List<Integer> nums = new ArrayList<>();
        while (sc.hasNextInt()) nums.add(sc.nextInt());
        sc.close();
        System.out.println("Antes: " + nums);

        int MEM = 3; 
        List<File> partes = new ArrayList<>();
        for (int i = 0; i < nums.size(); i += MEM) {
            List<Integer> sub = nums.subList(i, Math.min(i+MEM, nums.size()));
            Collections.sort(sub);
            File f = File.createTempFile("p", ".txt");
            try (PrintWriter pw = new PrintWriter(f)) for (int n: sub) pw.println(n);
            partes.add(f);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n->n.val));
        List<BufferedReader> rs = new ArrayList<>();
        for (int i=0;i<partes.size();i++){
            BufferedReader br=new BufferedReader(new FileReader(partes.get(i))); rs.add(br);
            String s=br.readLine(); if(s!=null) pq.add(new Node(Integer.parseInt(s),i));
        }
        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty()){
            Node n=pq.poll(); res.add(n.val);
            String s=rs.get(n.idx).readLine(); if(s!=null) pq.add(new Node(Integer.parseInt(s),n.idx));
        }
        for(BufferedReader br:rs) br.close();
        try(PrintWriter pw=new PrintWriter("salida.txt")) for(int n:res) pw.print(n+" ");

        System.out.println("Despues: " + res);
    }
    static class Node{int val,idx;Node(int v,int i){val=v;idx=i;}}
}
