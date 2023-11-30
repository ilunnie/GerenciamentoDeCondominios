import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { useState } from 'react';
import { UtilsContext } from './src/config/context';
import { styles } from './src/styles/styles';

//! █▓▒▒░░░PAGES░░░▒▒▓█ !\\
import Cadastrar from './src/views/Cadastrar';
import Entrar from './src/views/Entrar';
import Senha from './src/views/Senha';
import Nome from './src/views/Nome';
import Home from './src/views/Home';
import Nav from './src/views/Nav';

export default function App() {
  const [utils, setUtils] = useState({})
  const Stack = createStackNavigator()
  // localStorage.removeItem("token");
  return (
    <NavigationContainer>
      <UtilsContext.Provider value={{ utils, setUtils }}>
        <Stack.Navigator screenOptions={{ cardStyle: styles.body }}>
          <Stack.Screen name="Entrar" component={Entrar} options={{ headerShown: false }} />
          <Stack.Screen name="Senha" component={Senha} options={{ headerShown: false }} />
          <Stack.Screen name="Nome" component={Nome} options={{ headerShown: false }} />
          <Stack.Screen name="Cadastrar" component={Cadastrar} options={{ headerShown: false }} />
          <Stack.Screen name="Home" component={Home} options={{ headerShown: false }} />
          <Stack.Screen name="Nav" component={Nav} options={{ headerShown: false }} />
        </Stack.Navigator>
      </UtilsContext.Provider>
    </NavigationContainer>
  );
}