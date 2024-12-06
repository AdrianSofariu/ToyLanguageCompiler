package model.statements;

import exceptions.ADTException;
import exceptions.ExpressionException;
import exceptions.StatementException;
import model.expressions.IExpression;
import model.state.PrgState;
import model.types.RefType;
import model.values.IValue;
import model.values.RefValue;

public class HeapAllocStatement implements IStatement{

    String var;
    IExpression expression;

    public HeapAllocStatement(String var, IExpression expression){
        this.var = var;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws StatementException, ExpressionException, ADTException {
        if(!state.getSymTable().contains(var)){
            throw new StatementException("Variable " + var + " is not defined");
        }

        IValue varValue = state.getSymTable().get(var);

        if(!(varValue.getType() instanceof RefType)){
            throw new StatementException("Variable " + var + " is not a reference");
        }

        IValue expValue = expression.eval(state.getSymTable(), state.getHeap());

        //cast varValue to RefValue and check that the locationType is the same with the expression type
        if(!((RefValue)varValue).getLocationType().equals(expValue.getType())){
            throw new StatementException("Expression value is not of the same type as the reference type");
        }

        int addr = state.getHeap().allocate(expValue);
        state.getSymTable().insert(var, new RefValue(addr, ((RefValue) varValue).getLocationType()));

        return state;
    }

    public String toString(){
        return "new(" + var + ", " + expression + ")";
    }

    @Override
    public IStatement deepCopy() {
        return new HeapAllocStatement(var, expression.deepCopy());
    }
}
