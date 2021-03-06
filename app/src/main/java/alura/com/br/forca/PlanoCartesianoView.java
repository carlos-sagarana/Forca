package alura.com.br.forca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by carloseduardo on 25/07/15.
 */
public class PlanoCartesianoView extends View{

    private int menorLadoDisplay;

    private int unidade;

    private boolean desenhaPlanoCarteseano = false;

    public PlanoCartesianoView(Context context) {
        super(context);
    }

    public PlanoCartesianoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public int getMenorLadoDisplay() {
        return menorLadoDisplay;
    }

    public void setMenorLadoDisplay(int menorLadoDisplay) {
        this.menorLadoDisplay = menorLadoDisplay;
    }

    public boolean getDesenhaPlanoCarteseano() {
        return desenhaPlanoCarteseano;
    }

    public void setDesenhaPlanoCarteseano(boolean desenhaPlanoCarteseano) {
        this.desenhaPlanoCarteseano = desenhaPlanoCarteseano;
    }

    public int getUnidade() {
        return unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(getHeight() > getWidth()) {
            setMenorLadoDisplay(getWidth());
        } else {
            setMenorLadoDisplay(getHeight());
        }

        setUnidade(getMenorLadoDisplay()/10);

        if (getDesenhaPlanoCarteseano()) {
            drawPlanoCartesiano(canvas);
        }
    }

    public void drawPlanoCartesiano(Canvas canvas) {
        Path path = new Path();

        int max = toPixel(10);

        for (int n = 0; n <= 10; n++) {
            //Desenhando as linhas verticais
            path.moveTo(toPixel(n), 1);
            path.lineTo(toPixel(n), max);

            //Desenhando as linhas horizontais
            path.moveTo(1, toPixel(n));
            path.lineTo(max, toPixel(n));
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true); //Propriedade que define a suavidade da linha
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        canvas.drawPath(path, paint);
    }

    public int toPixel(int vezes) {
        return vezes * getUnidade();
    }
}
