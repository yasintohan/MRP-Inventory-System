import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class mainClass {


    public static int item314amount = 0;
    public static int item14127amount = 60;
    public static int item019amount = 50;
    public static int item2142amount = 80;
    public static int item129amount = 0;
    public static int item1118amount = 30;
    public static int item11495amount = 120;
    public static int item457amount = 0;
    public static int item062amount = 50;
    public static int item13122amount = 0;
    public static int item048amount = 30;
    public static int item118amount = 0;
    public static int item1605amount = 30;



    public static Item item457 = new Item("457", item457amount,20,2,2,1,1, new Item[]{});
    public static Item item062r2 = new Item("062", item062amount,100,6,2,1,2, new Item[]{});
    public static Item item1118 = new Item("1118", item1118amount,0,0,3,1,1, new Item[]{});
    public static Item item129 = new Item("129", item129amount,100,8,4,40,1, new Item[]{});
    public static Item item11495 = new Item("11495", item11495amount,0,0,1,50,1, new Item[]{item129,item1118});
    public static Item item13122 = new Item("13122", item13122amount,70,3,1,40,1, new Item[]{item457,item062r2,item11495});
    public static Item item048 = new Item("048", item048amount,0,0,3,30,1, new Item[]{});
    public static Item item118 = new Item("118", item118amount,50,2,2,1,1, new Item[]{});
    public static Item item062r4 = new Item("062", item062amount,100,6,2,1,4, new Item[]{});
    public static Item item14127r4 = new Item("14127", item14127amount,0,0,2,50,4, new Item[]{});
    public static Item item2142 = new Item("2142", item2142amount,0,0,2,100,1, new Item[]{});
    public static Item item019 = new Item("019", item019amount,40,5,2,50,1, new Item[]{});
    public static Item item14127r6 = new Item("14127", item14127amount,0,0,2,50,6, new Item[]{});
    public static Item item314 = new Item("314", item314amount,50,5,1,50,1, new Item[]{item2142, item019, item14127r6});
    public static Item item1605 = new Item("1605", item1605amount,0,0,1,1,1, new Item[]{item13122,item048,item118,item062r4,item14127r4,item314});



    public mainClass() {
    }

    public static void main(String[] args) {
        boolean test = true;



        while (test) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1-print inventory \n" +
                    "2-order item \n"+
                    "3-show items \n"+
                    "0-exit \n");

            int number = scanner.nextInt();


            switch (number) {
                case 1:
                    System.out.println("Inventory");
                    for(int i = 0;i<46;i++){ System.out.print("-"); }
                    System.out.println();
                    printInv(item1605);
                    break;
                case 2:
                    //case2
                    Item item = null;
                    System.out.println("Select item number\n");
                    int orderNumber = scanner.nextInt();
                    boolean test2 = true;
                    switch (orderNumber) {
                        case 457:
                            item = item457;
                            break;
                        case 62:
                            //r sorcak
                            item = item062r2;
                            break;
                        case 129:
                            item = item129;
                            break;
                        case 1118:
                            item = item1118;
                            break;
                        case 11495:
                            item = item11495;
                            break;
                        case 13122:
                            item = item13122;
                            break;
                        case 48:
                            item = item048;
                            break;
                        case 118:
                            item = item118;
                            break;
                        case 14127:
                            //r
                            item = item14127r6;
                            break;
                        case 2142:
                            item = item2142;
                            break;
                        case 19:
                            item = item019;
                            break;
                        case 314:
                            item = item314;
                            break;
                        case 1605:
                            item = item1605;
                            break;
                        default:
                            System.out.println("item not found\n");
                            test2 = false;
                            break;

                    }
                    if(test2){
                    int[] grossReq = new int[10];

                    for(int i = 1;i<11;i++){
                        System.out.println("Select " + i + ". week Gross Requirements");
                        int orderGrossReq = scanner.nextInt();
                        grossReq[i-1]=orderGrossReq;
                    }


                    calculate(item, grossReq);
                    }
                    //#case2
                    break;
                case 3:
                    printTree(item1605,1);
                    break;
                case 0:
                    test = false;
                    break;

            }

        }

    }


    public static void calculate(Item item, int[] grossReq) {

        int[] netreq = new int[10];
        int[] scheduled = new int[10];
        int[] amofhand = new int[10];
        int[] plannedorder = new int[10];
        int[] childgrossReq = new int[10];
        amofhand[0] = item.getAmountOnHand();
        if(item.getArrivalOnWeek()!=0)
            scheduled[item.getArrivalOnWeek()-1]=item.getScheduledReceipt();

        for(int j=1;j<10;j++){



            if(amofhand[j-1]+scheduled[j-1] >= grossReq[j-1]) {
                amofhand[j] = amofhand[j - 1]+scheduled[j-1] - grossReq[j - 1];
            }else if(amofhand[j-1]+scheduled[j-1] < grossReq[j-1]) {
                netreq[j-1] = grossReq[j - 1] - amofhand[j - 1]-scheduled[j-1] ;
                plannedorder[j-1] = (int)(Math.ceil((float)netreq[j-1] / (float)item.getLotSizingRule())*item.getLotSizingRule());
                amofhand[j] = amofhand[j]+ plannedorder[j-1] + scheduled[j-1] +amofhand[j-1]- grossReq[j - 1];
            }


        }


        if(item.getChilds().length!=0){
            for(int k = 0; k<item.getChilds().length;k++){
                for(int i=0;i<plannedorder.length-1;i++){
                    childgrossReq[i]=plannedorder[i+1]*item.getChilds(k).getItemRequired();
                }
                calculate(item.getChilds(k),childgrossReq);
            }
        }

        item.printTable(item,grossReq,amofhand,netreq,plannedorder);
        item.setAmountOnHand(amofhand[9]);




    }


    public static void printTree(Item item,int j){
        if(item.getItemRequired() != 1)
            System.out.print("└──" + item.getItemId()+ "("+ item.getItemRequired() + " required)" +"\n");
        else
            System.out.print("└──" + item.getItemId() +"\n");
        if(item.getChilds().length !=0) {
           for(int i = 0; i<item.getChilds().length;i++){
               System.out.printf("%-" +  5*j + "s","");
               printTree(item.getChilds(i),j+1);
           }
        }

    }

    public static void printInv(Item item){

        System.out.printf("%-20s","Item Id: " + item.getItemId());
        System.out.printf("%-25s","│ Amount on Hand: " + item.getAmountOnHand());
        System.out.println();
        for(int i = 0;i<46;i++){ System.out.print("-"); }
        System.out.println();

        if(item.getChilds().length !=0) {
            for(int i = 0; i<item.getChilds().length;i++){
                printInv(item.getChilds(i));
            }
        }
    }


}
