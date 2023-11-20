import { View, TouchableOpacity } from 'react-native';
import { FontAwesomeIcon } from '@fortawesome/react-native-fontawesome';
import { faMoon } from '@fortawesome/free-solid-svg-icons';
import { styles, setTheme } from '../styles/styles';

export default function ThemeButton(props) {
    return (
        <View style={{zIndex: "10", position: "absolute", right: "2%", top: "2%"}}>
            <TouchableOpacity onPress={() => setTheme()}>
                <FontAwesomeIcon
                    icon={faMoon}
                    size={props.size || 25}
                    style={ props.style ? [...styles.icon, ...props.style] : styles.icon }
                />
            </TouchableOpacity>
        </View>
    );
}