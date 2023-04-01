//package testes;
//
//import javax.persistence.EntityManager;
//
//import pessoas.Pessoa;
//import util.JpaUtil;
//
//public class PessoaSemDao {
//
//    public static void main(String[] args) {
//
//        EntityManager em = JpaUtil.getEntityManager();
//
//        var p1 = new Pessoa("pessoa de T3");
//
//        em.getTransaction().begin();
//
//        em.persist(p1);
//
//        em.getTransaction().commit();
//
//        em.close();
//        JpaUtil.close();
//
//        System.out.println("fim PessoaSemDao");
//    }
//
//}
