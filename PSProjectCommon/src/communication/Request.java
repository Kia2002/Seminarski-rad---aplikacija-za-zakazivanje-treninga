/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author Aleksa
 */
public class Request implements Serializable{
    private Operation operation;
    private Object parameter;

    public Request() {
    }

    public Request(Operation operation) {
        this.operation = operation;
    }

    public Request(Operation operation, Object parameter) {
        this.operation = operation;
        this.parameter = parameter;
    }

    public Operation getOperation() {
        return operation;
    }

    public Object getParameter() {
        return parameter;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return "Request{" + "operation=" + operation + ", parameter=" + parameter + '}';
    }
    
    
}
