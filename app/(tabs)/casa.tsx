// app/(tabs)/casa.tsx
import { View, Text, ImageBackground, TouchableOpacity } from 'react-native';
import { useRouter } from 'expo-router';
import { PlayCircle } from 'lucide-react-native';

export default function Casa() {
  const router = useRouter();

  return (
    <ImageBackground
      source={require('../../assets/images/background_home.png')}
      resizeMode="cover"
      style={{
        flex: 1,
        justifyContent: 'flex-end',
        padding: 20,
      }}
    >
      <View style={{
        backgroundColor: 'rgba(59, 32, 0, 0.85)',
        padding: 20,
        borderRadius: 25,
        marginBottom: 40,
      }}>
        <Text style={{ fontSize: 24, fontWeight: 'bold', color: '#ffffffdd' }}>
          O Pequeno Urso
        </Text>

        <Text style={{ fontSize: 16, marginTop: 8, color: '#ffffffdd' }}>
          O Pequeno Urso vive em uma cabana com seus pais, o Papai-Urso e a Mamãe-Urso, e passa seus dias explorando a floresta com seus amigos. 
        </Text>

        <TouchableOpacity
          onPress={() => router.push('/assistir')}
          style={{
            marginTop: 20,
            backgroundColor: '#88C0D0',
            paddingVertical: 15,
            paddingHorizontal: 110,
            borderRadius: 30,
            flexDirection: 'row',
            alignItems: 'center',
            alignSelf: 'flex-start',
            shadowColor: '#000',
            shadowOpacity: 0.5,
            shadowRadius: 4,
            elevation: 2,
          }}
        >
          <PlayCircle color="#fff" size={24} />
          <Text style={{ color: 'white', fontWeight: '900', marginLeft: 10 }}>
            Assistir
          </Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}
