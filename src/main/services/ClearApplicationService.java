package services;
import dataAccess.*;

import responses.ClearApplicationResponse;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The ClearApplicationService class provides a service to clear application data and returns a ClearApplicationResponse.
 */
public class ClearApplicationService {

        /**
         * Clears application data and provides a response indicating the result.
         *
         * @return A ClearApplicationResponse indicating the result of the clear application operation.
         */
        public ClearApplicationResponse clearApplication() {
                GameDAO g = new GameDAO();
                UserDAO u = new UserDAO();
                AuthDAO a = new AuthDAO();
                //clear
                try (Connection conn = new Database().getConnection()) {
                        g.clear(conn);
                        u.clear(conn);
                        a.clear(conn);
                }
                catch (DataAccessException | SQLException e) {
                        return new ClearApplicationResponse(e.getMessage());
                }

            return new ClearApplicationResponse();
        }
}
