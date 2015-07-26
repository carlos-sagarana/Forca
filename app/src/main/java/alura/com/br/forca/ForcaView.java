package alura.com.br.forca;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by carloseduardo on 25/07/15.
 */
public class ForcaView extends PlanoCartesianoView {

    private Path pathForca;
    private Paint paintForca;

    private ForcaController forcaController;



    private enum Membro{braco, perna;}

    private enum Lado{direito, esquerdo;}
    public ForcaView(Context context) {
        super(context);
        setPathForca(new Path());
    }

    public ForcaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setPathForca(new Path());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        setDesenhaPlanoCarteseano(true);
        plotaArmacaoDaForca();

        if(getForcaController() != null) {

            switch (getForcaController().getQuantidadeErros()) {
                case 0:
                    plotaCabeca();
                    break;

                case 1:
                    plotaCorpo();
                    break;

                case 2:
                    plotaMembro(Membro.braco, Lado.direito);
                    break;

                case 3:
                    plotaMembro(Membro.braco, Lado.esquerdo);
                    break;

                case 4:
                    plotaMembro(Membro.perna, Lado.direito);
                    break;

                case 5:
                    plotaMembro(Membro.perna, Lado.esquerdo);
                    break;
            }
        }

        drawLetrasCorretas(canvas);
        plotaTracos();

        canvas.drawPath(getPathForca(), getPaintForca());

    }

    public ForcaController getForcaController() {
        return forcaController;
    }

    public void setForcaController(ForcaController forcaController) {
        this.forcaController = forcaController;
    }

    public Path getPathForca() {
        return pathForca;
    }

    public void setPathForca(Path pathForca) {
        this.pathForca = pathForca;
    }

    public Paint getPaintForca() {
        paintForca = new Paint();
        paintForca.setColor(Color.BLACK);
        paintForca.setStyle(Paint.Style.STROKE);
        paintForca.setStrokeWidth(8);
        return paintForca;
    }

    private void plotaArmacaoDaForca() {
        getPathForca().moveTo(toPixel(1), toPixel(10));
       getPathForca().lineTo(toPixel(3), toPixel(10));

        getPathForca().moveTo(toPixel(2), toPixel(10));
        getPathForca().lineTo(toPixel(2), toPixel(1));

        getPathForca().rLineTo(toPixel(5), 0);
        getPathForca().rLineTo(0, toPixel(1));

    }

    private void plotaCabeca() {
        getPathForca().addCircle(toPixel(7), toPixel(3), toPixel(1), Path.Direction.CW);
    }

    private void plotaCorpo() {
        getPathForca().moveTo(toPixel(7), toPixel(4));
        getPathForca().lineTo(toPixel(7), toPixel(7));
    }

    private void plotaMembro(Membro membro, Lado lado) {
        final int posicaoDoCorpo = 7;
        final int alturaDoBraco = 5;
        final int alturaDaPerna = 7;

        int alturaFinal;

        if (membro == Membro.braco) {
            getPathForca().moveTo(toPixel(posicaoDoCorpo), toPixel(alturaDoBraco));
            alturaFinal = alturaDoBraco + 1;
        } else {
            getPathForca().moveTo(toPixel(posicaoDoCorpo), toPixel(alturaDaPerna));
            alturaFinal = alturaDaPerna + 1;
        }

        if (lado == Lado.direito) {
            getPathForca().lineTo(toPixel(posicaoDoCorpo + 1), toPixel(alturaFinal));
        } else {
            getPathForca().lineTo(toPixel(posicaoDoCorpo - 1), toPixel(alturaFinal));
        }
    }

    private Paint getPaintTraco() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setTextSize(25);

        return paint;
    }

    private void plotaTracos() {
        int eixoX = toPixel(3);
        getPathForca().moveTo(eixoX + 10, toPixel(10));

        if (getForcaController() == null) {
            return;
        }

        for (int i = 0; i <= getForcaController().getPalavraAteAgora().length() -1; i++) {
            getPathForca().rMoveTo(10, 0);
            getPathForca().rLineTo(toPixel(1), 0);
        }
    }

    private void drawLetrasCorretas(Canvas canvas) {
        int eixoX = toPixel(3);
        getPathForca().moveTo(eixoX + 10, toPixel(10));
        eixoX += 35;

        if(getForcaController() == null) {
            return;
        }

        for(int i = 0; i <= getForcaController().getPalavraAteAgora().length() -1; i++) {
            char c = getForcaController().getPalavraAteAgora().charAt(i);
            canvas.drawText(c + "", eixoX + ((toPixel(1) + 10) * i), toPixel(10) -15, getPaintTraco());
        }
    }
}
