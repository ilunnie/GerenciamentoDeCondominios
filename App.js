import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

//! █▓▒▒░░░PAGES░░░▒▒▓█ !\\
import Nav from './src/views/Nav';

export default function App() {
  const Stack = createStackNavigator()
  
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Nav" component={Nav} options={{ headerShown: false }}/>
      </Stack.Navigator>
    </NavigationContainer>
  );
}