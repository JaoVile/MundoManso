import React from 'react';
import { View, Text, FlatList, Image, TouchableOpacity, StyleSheet } from 'react-native';
import { useRouter } from 'expo-router';

const filmes = [
  {
    id: '1',
    titulo: 'O PEQUENO URSO',
    imagem: require('../../assets/videos/O_PEQUENO_URSO.png'),
  },
  {
    id: '2',
    titulo: 'O Reino Encantado',
    imagem: require('../../assets/videos/reino.png'),
  },
  {
    id: '3',
    titulo: 'Brincando na Floresta',
    imagem: require('../../assets/videos/floresta.png'),
  },
];

export default function CasaScreen() {
  const router = useRouter();

  return (
    <View style={styles.container}>
      <Text style={styles.titulo}>Hora do Cinema 🎬</Text>
      <TouchableOpacity style={styles.btnVoltar} onPress={() => router.push('/')}>
      <Text style={styles.txtVoltar}>Voltar para o início</Text>
      </TouchableOpacity>

      <FlatList
        data={filmes}
        keyExtractor={(item) => item.id}
        renderItem={({ item }) => (
          <TouchableOpacity
            style={styles.card}
            onPress={() => router.push('./detalhe')}
          >
            <Image source={item.imagem} style={styles.imagem} />
            <Text style={styles.nome}>{item.titulo}</Text>
          </TouchableOpacity>
        )}
        numColumns={2}
        contentContainerStyle={styles.lista}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: { flex: 1, backgroundColor: '#fff', paddingTop: 50 },
  titulo: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
  },
  lista: {
    paddingHorizontal: 10,
    gap: 10,
    justifyContent: 'center',
  },
  card: {
    flex: 1,
    margin: 5,
    alignItems: 'center',
    backgroundColor: '#f2f2f2',
    borderRadius: 12,
    padding: 10,
  },
  imagem: {
    width: 120,
    height: 120,
    borderRadius: 8,
    marginBottom: 8,
  },
  nome: {
    fontSize: 14,
    fontWeight: 'bold',
    textAlign: 'center',
  },
  btnVoltar: {
  marginLeft: 20,
  marginBottom: 6,
  backgroundColor: '#ddd',
  paddingVertical: 5,
  paddingHorizontal: 10,
  borderRadius: 20,
  alignSelf: 'flex-start',
},
txtVoltar: {
  color: '#665544',
  fontWeight: 'bold',
},

});
