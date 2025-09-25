public class Order {

    private OrderNode firstNode;

    private OrderNode lastNode;

    private int count = 0;

    public Order () {

        this.firstNode = null;
        this.lastNode = null;
        this.count = 0;

    }

    public boolean isEmpty() {
        return (firstNode == null) && (lastNode == null);
    }

    public void enqueue(String lastName, int id, double cost) {

        OrderNode newNode = new OrderNode(lastName,id,cost);

        if (this.isEmpty()) {
            firstNode = newNode;
        }
        else {
            lastNode.setNext(newNode);
        }

        lastNode = newNode;
        count++;

    }

    private OrderNode getFront() {
        return firstNode;
    }

    public String getName() {
        return firstNode.getOrderName();
    }

    public int getOrderNumber() {
        return firstNode.getOrderNumber();
    }

    public void dequeue() {
        OrderNode front = this.getFront();
        firstNode = firstNode.getNext();
        if (firstNode == null) {
            lastNode = null;
        }
        count--;
    }


    public int getCurrentSize() {
        return this.count;
    }

    private class OrderNode {

        private String orderName;

        private int orderNumber;

        private double orderCost;

        private OrderNode next;

        private OrderNode(String lastName, int id, double cost) {
            this.orderName = lastName;
            this.orderNumber = id;
            this.orderCost = cost;
        }

        private String getOrderName() {
            return this.orderName;
        }

        private int getOrderNumber() {
            return this.orderNumber;
        }

        private double getOrderCost() {
            return this.orderCost;
        }

        private OrderNode getNext() {
            return this.next;
        }

        private void setNext (OrderNode next) {
            this.next = next;
        }

    }

}
