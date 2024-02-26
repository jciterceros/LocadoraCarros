package LocadoraCarros.repositorys;

import LocadoraCarros.classe.ConexaoBanco;
import LocadoraCarros.model.Carro;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarroRepository {

    // Consultar todos os carros
    public List<Carro> consultar() {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();

            String sql = "SELECT * FROM carro";

            ResultSet result = statement.executeQuery(sql);

            List<Carro> listaCarro = new ArrayList<>();

            while (result.next()) {
                listaCarro.add(new Carro(
                        result.getLong("id"),
                        result.getLong("id_fabricante"),
                        result.getLong("id_modelo"),
                        result.getString("placa"),
                        result.getString("cor"),
                        result.getBoolean("disponivel"),
                        result.getInt("ano"),
                        result.getDouble("valorlocacao")));
            }

            return listaCarro;

        } catch (Exception ex) {
            System.out.println("Algo deu errado");
            return null;
        }
    }

    public void salvar(Carro pCarro) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String insertCarro = "INSERT INTO carro (id_fabricante, id_modelo, placa," +
                    "cor, disponivel, ano, valorlocacao) VALUES ("
                    + pCarro.getIdFabricante() + ","
                    + pCarro.getIdModelo() + ", '"
                    + pCarro.getPlaca() + "', '"
                    + pCarro.getCor() + "', "
                    + pCarro.getDisponivel() + ", "
                    + pCarro.getAno() + ", "
                    + pCarro.getValorLocacao() + ")";

            // StringBuilder insertCarro2 = new StringBuilder();
            // insertCarro2.append("INSERT INTO carro (id_fabricante, id_modelo, placa,");
            // insertCarro2.append(" cor, disponivel, ano, valorlocacao) VALUES (");
            // insertCarro2.append(pCarro.getIdFabricante()).append(",");
            // insertCarro2.append(pCarro.getIdModelo()).append(", ");
            // insertCarro2.append("'").append(pCarro.getPlaca()).append("'").append(",");
            // insertCarro2.append("'").append(pCarro.getCor()).append("'").append(",");
            // insertCarro2.append(pCarro.getDisponivel()).append(", ");
            // insertCarro2.append(pCarro.getAno()).append(",");
            // insertCarro2.append(pCarro.getValorLocacao());
            // insertCarro2.append(");");

            statement.execute(insertCarro);
            // statement.execute(insertCarro2.toString());

        } catch (Exception ex) {
            System.out.println("Algo deu errado");
        }
    }

    // Atualizar carro
    public void atualizar(Carro pCarro) {
        try {
            Statement statement = ConexaoBanco.getConn().createStatement();
            String updateCarro = "UPDATE carro SET id_fabricante = "
                    + pCarro.getIdFabricante()
                    + ", id_modelo = " + pCarro.getIdModelo()
                    + ", placa = '" + pCarro.getPlaca()
                    + "', cor = '" + pCarro.getCor()
                    + "', disponivel = " + pCarro.getDisponivel()
                    + ", ano = " + pCarro.getAno()
                    + ", valorlocacao = " + pCarro.getValorLocacao()
                    + " WHERE id = " + pCarro.getId();

            statement.execute(updateCarro);

        } catch (Exception ex) {
            System.out.println("Algo deu errado");
        }
    }
}
