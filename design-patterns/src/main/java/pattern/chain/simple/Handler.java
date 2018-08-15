package pattern.chain.simple;

public abstract class Handler {

    private Handler sucessor;

    public Handler getSucessor(){
        return sucessor;
    }

    public void setSucessor(Handler sucessor){
        this.sucessor = sucessor;
    }

    public void excute(){
        handleProcess();
        if(sucessor != null){
            sucessor.excute();
        }
    }

    /**
     * 子类自己要处理的逻辑
     */
    protected abstract void handleProcess();
}
