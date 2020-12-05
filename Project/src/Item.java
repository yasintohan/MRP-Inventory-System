public class Item {

    private String itemId;
    private int amountOnHand;
    private int scheduledReceipt;
    private int arrivalOnWeek;
    private int leadTime;
    private int lotSizingRule;
    private int itemRequired = 1;
    private Item[] childs = null;



    public Item( String itemId, int amountOnHand, int scheduledReceipt, int arrivalOnWeek, int leadTime, int lotSizingRule,int itemRequired, Item[] childs) {
        this.itemId = itemId;
        this.amountOnHand = amountOnHand;
        this.scheduledReceipt = scheduledReceipt;
        this.arrivalOnWeek = arrivalOnWeek;
        this.leadTime = leadTime;
        this.lotSizingRule = lotSizingRule;
        this.itemRequired = itemRequired;
        this.childs = childs;
    }



    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAmountOnHand() {
        return amountOnHand;
    }

    public void setAmountOnHand(int amountOnHand) {
        this.amountOnHand = amountOnHand;
    }

    public int getScheduledReceipt() {
        return scheduledReceipt;
    }

    public void setScheduledReceipt(int scheduledReceipt) {
        this.scheduledReceipt = scheduledReceipt;
    }

    public int getArrivalOnWeek() {
        return arrivalOnWeek;
    }

    public void setArrivalOnWeek(int arrivalOnWeek) {
        this.arrivalOnWeek = arrivalOnWeek;
    }

    public int getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(int leadTime) {
        this.leadTime = leadTime;
    }

    public int getLotSizingRule() {
        return lotSizingRule;
    }

    public void setLotSizingRule(int lotSizingRule) {
        this.lotSizingRule = lotSizingRule;
    }


    public Item[] getChilds() {
        return childs;
    }

    public Item getChilds(int i) {
        return childs[i];
    }

    public void setChilds(Item[] childs) {
        this.childs = childs;
    }

    public int getItemRequired() {
        return itemRequired;
    }

    public void setItemRequired(int itemRequired) {
        this.itemRequired = itemRequired;
    }






    public void printTable(Item item, int[] grossreq, int[] amofhand, int[] netreq, int[] plannedorder) {



        String[][] table = {{"","Period",                                           "1","2","3","4","5","6","7","8","9","10"},
                {"itemid =" + item.getItemId(),              "Gross Requirements",   "","","","","","","","","",""},
                {"",                    "Scheduled Receipts",                        "","","","","","","","","",""},
                {"LT =" + item.getLeadTime(), "On hand From prior period",           "","","","","","","","","",""},
                {"",                    "Net Requirements",                          "","","","","","","","","",""},
                {"Q =","Time-phased Net Requirements",      "","","","","","","","","",""},
                {"",                    "Planned Order releases",                    "","","","","","","","","",""},
                {"",                    "Planned Order delivery",                    "","","","","","","","","",""},
        };

        if(item.getLotSizingRule()==1)
            table[5][0] = "Q = L4L";
        else
            table[5][0] = "Q =" + item.getLotSizingRule();

        if(arrivalOnWeek!=0)
        table[2][item.arrivalOnWeek+1] = item.scheduledReceipt + "";

        //grossreq print
        for(int i = 0; i<grossreq.length; i++) {
            table[1][i+2] = String.valueOf(grossreq[i]);
            table[3][i+2] = String.valueOf(amofhand[i]);
            table[4][i+2] = String.valueOf(netreq[i]);
            if(i+2-item.getLeadTime() > 1) {
                table[5][i + 2 - item.getLeadTime()] = String.valueOf(netreq[i]);
                table[6][i + 2 - item.getLeadTime()] = String.valueOf(plannedorder[i]);
            }
            table[7][i+2] = String.valueOf(plannedorder[i]);


        }
        for(int j = 0;j<item.getLeadTime();j++){
                table[5][table[1].length-1-j] = "0";
                table[6][table[1].length-1-j] = "0";

        }
        //#grossreq print

        for(int i = 0;i<105;i++){ System.out.print("-"); }
        System.out.println("\n");
        for(int i = 0; i<table.length; i++)
        {
            for(int j=0; j<table[i].length;j++){
                if(j == 0) {
                    System.out.printf("%-15s", table[i][j]);

                } else if(j==1){
                    System.out.printf("%-30s", table[i][j]);
                } else {
                    System.out.printf("%-6s", table[i][j]);
                }
            }
            System.out.println("\n");
        }
        for(int i = 0;i<105;i++){ System.out.print("-"); }
        System.out.println("\n");




    }
}
