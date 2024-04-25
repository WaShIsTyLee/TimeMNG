    package github.washistylee.Model.DAO;


    import github.washistylee.Model.Entitys.Person;

    public class Sesion {
        /**
         * Metodos relacionados con el manejo de la sesión dentro del progrma.
         */
        private static Sesion _instance;
        private static Person usuarioIniciado;

        private Sesion() {
        }

        public static Sesion getInstancia() {
            if (_instance == null) {
                _instance = new Sesion();

            }
            return _instance;
        }

        public void logIn(Person user) {
            usuarioIniciado = user;
        }

        public static Person getUsuarioIniciado() {
            return usuarioIniciado;
        }


    }
