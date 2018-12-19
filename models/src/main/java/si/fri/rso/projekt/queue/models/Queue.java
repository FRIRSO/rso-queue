package si.fri.rso.projekt.queue.models;

public class Queue {

    private int delivererID;
    private int queueID;
    private int orderID;

    public Queue(int delivererID, int queueID, int orderID) {
        this.queueID = queueID;
        this.orderID = orderID;
        this.delivererID = delivererID;
    }

    public int getDelivererID() {
        return delivererID;
    }

    public void setDelivererID(int delivererID) {
        this.delivererID = delivererID;
    }

    public int getQueueID() {
        return queueID;
    }

    public void setQueueID(int queueID) {
        this.queueID = queueID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
}
