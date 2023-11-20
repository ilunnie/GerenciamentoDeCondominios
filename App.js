import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { styles, setTheme } from './src/styles/styles';

//! █▓▒▒░░░PAGES░░░▒▒▓█ !\\
import Entrar from './src/views/Entrar';
import Senha from './src/views/Senha';
import Nav from './src/views/Nav';

export default function App() {
  const Stack = createStackNavigator()
  
  return (
    <NavigationContainer>
      <Stack.Navigator screenOptions={{cardStyle: styles.body}}>
        <Stack.Screen name="Entrar" component={Entrar} options={{ headerShown: false }}/>
        <Stack.Screen name="Senha" component={Senha} options={{ headerShown: false }}/>
        <Stack.Screen name="Nav" component={Nav} options={{ headerShown: false }}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}