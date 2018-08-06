package io.jcervelin.broker.message.domains;

public class Action {
    private String action;
    private Sale sale;
    private double amount;
    private String type;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Action{" +
                "action='" + action + '\'' +
                ", sale=" + sale +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Action)) return false;

        Action action1 = (Action) o;

        if (Double.compare(action1.amount, amount) != 0) return false;
        if (action != null ? !action.equals(action1.action) : action1.action != null) return false;
        if (sale != null ? !sale.equals(action1.sale) : action1.sale != null) return false;
        return type != null ? type.equals(action1.type) : action1.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = action != null ? action.hashCode() : 0;
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
