package pe.com.yzm.core.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <b>Class</b>: UrlPermited <br/>
 * <b>Copyright</b>: 2024 Yovanny Zeballos Medina<br/>.
 *
 * @author 2024  Yovanny Zeballos Medina <br/>
 * <u>Developed by</u>: Yovanny Zeballos <br/>
 * <u>Changes:</u><br/>
 * <ul>
 *   <li>
 *     setiembre 18, 2024 Creación de Clase.
 *   </li>
 * </ul>
 */
@Getter
@AllArgsConstructor
public enum UrlPermited {
    SWAGGER("swagger"),
    API_DOC("api-doc"),
    HEALTH("health");

    private String value;
}
