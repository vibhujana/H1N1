public class Main {
    public static void main(String[] args) {
//        Model(Pop_size,Initial_S,Initial_I,alpha,beta,stepSize);
        int people = 50000;

//        Model(people-10,10,0,0.000005,0.143,1,1);

        System.out.println(rate(people-10,10,0,0.0000025,0.143,1,1,Integer.MIN_VALUE,-1));
    }




    private static void Model(double InitialS, double Initial_I, double Initial_R, double alpha, double beta, double stepSize,int count) {
        if(Initial_R < 0){
            return;
        }
        double newS = InitialS - (alpha * InitialS * Initial_I);

        double newR = Initial_R + (beta*Initial_I);
        double newI = Initial_I + ((alpha * InitialS * Initial_I) - (beta*Initial_I));
        System.out.println(count+": "+ newS + "," + newI + "," + newR);

        count++;
        Model(newS,newI,newR,alpha,beta,stepSize,count);
    }


    private static int rate(double InitialS, double Initial_I, double Initial_R, double alpha, double beta, double stepSize,int count,double max,int loc) {
        if(count > 1500){
            return loc;
        }
        double dS = (alpha * InitialS * Initial_I);
        double dR = (beta*Initial_I);
        double dI = (dS - dR);
        double newS = InitialS - dS;
        double newR = Initial_R + dR;
        double newI = Initial_I + dI;
//        System.out.println(count+": "+ newS + "," + newI + "," + newR);
        max = Math.max(dI,max);
        System.out.println(dI);
        if(max == dI)
            loc = count;
        count++;
        return rate(newS,newI,newR,alpha,beta,stepSize,count,max,loc);
    }
    private static int totalIll(double InitialS, double Initial_I, double Initial_R, double alpha, double beta, double stepSize,int count,int time) {
        if(count > 100){
            return time;
        }
        double dS = (alpha * InitialS * Initial_I);
        double dR = (beta*Initial_I);
        double dI = (dS - dR);
        double newS = InitialS - dS;
        double newR = Initial_R + dR;
        double newI = Initial_I + dI;
//        System.out.println(count+": "+ newS + "," + newI + "," + newR);
        if(Math.abs(newI - Initial_I) < 1){
            time = count;
        }
        count++;
        return totalIll(newS,newI,newR,alpha,beta,stepSize,count,time);
    }

}
