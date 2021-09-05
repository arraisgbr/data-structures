public class Billiards{
    public static void main(String[] args){
        
        // configuração inicial do desenho
        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        // valores iniciais
        double px = 0., py = 0.; // posição bola
        double vx = 0, vy = 0; // força
        double dx, dy; // mouse
        double dt; // distancia ao quadrado
        double k = 0.05; // constante
        double radiusBall = 0.05; // tamanho bola
        double atrito = 0.012; // atrito

        while(true){

            if(StdDraw.isMousePressed()){
                // distancia entre clique do mouse e bola
                dx = px - StdDraw.mouseX();
                dy = py - StdDraw.mouseY();

                // calculo da intensidade
                double abscissas = Math.pow(dx, 2);
                double ordenadas = Math.pow(dy, 2);
                dt = abscissas + ordenadas;
                if(dt < 0.01) dt += 0.005;
                vx = (k * dx) / dt;
                vy = (k * dy) / dt;
            }

            // checando se a bola tocou as paredes
            if (Math.abs(px + vx) > 1.0 - radiusBall) vx = -vx;
            if (Math.abs(py + vy) > 1.0 - radiusBall) vy = -vy;

            // atualizando posições
            px += vx;
            py += vy;

            // considerando atrito 
            vx -= vx*atrito;
            vy -= vy*atrito;

            // limpando a tela
            StdDraw.clear(StdDraw.BLUE);

            // desenhando a bola
            StdDraw.setPenColor(StdDraw.WHITE); 
            StdDraw.filledCircle(px, py, radiusBall); 

            // exibindo a tela
            StdDraw.show();

            // pausa por 10 ms
            StdDraw.pause(10);
        }
    }
}